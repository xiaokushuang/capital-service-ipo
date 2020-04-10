package com.stock.capital.enterprise.exportWord.serviec;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XpwfUtils {

	/**
	 * @Description:删除指定位置的表格,被删除表格后的索引位置--
	 */
	public void deleteTableByIndex(XWPFDocument xdoc, int pos) {
		Iterator<IBodyElement> bodyElement = xdoc.getBodyElementsIterator();
		int eIndex = 0, tableIndex = -1;
		while (bodyElement.hasNext()) {
			IBodyElement element = bodyElement.next();
			BodyElementType elementType = element.getElementType();
			if (elementType == BodyElementType.TABLE) {
				tableIndex++;
				if (tableIndex == pos) {
					break;
				}
			}
			eIndex++;
		}
		xdoc.removeBodyElement(eIndex);
	}

	public XWPFTable getTableByIndex(XWPFDocument xdoc, int index) {
		List<XWPFTable> tablesList = getAllTable(xdoc);
		if (tablesList == null || index < 0 || index > tablesList.size()) {
			return null;
		}
		return tablesList.get(index);
	}

	public List<XWPFTable> getAllTable(XWPFDocument xdoc) {
		return xdoc.getTables();
	}

	/**
	 * @Description: 得到表格内容(第一次跨行单元格视为一个，第二次跳过跨行合并的单元格)
	 */
	public List<List<String>> getTableRContent(XWPFTable table) {
		List<List<String>> tableContentList = new ArrayList<List<String>>();
		for (int rowIndex = 0, rowLen = table.getNumberOfRows(); rowIndex < rowLen; rowIndex++) {
			XWPFTableRow row = table.getRow(rowIndex);
			List<String> cellContentList = new ArrayList<String>();
			for (int colIndex = 0, colLen = row.getTableCells().size(); colIndex < colLen; colIndex++) {
				XWPFTableCell cell = row.getCell(colIndex);
				CTTc ctTc = cell.getCTTc();
				if (ctTc.isSetTcPr()) {
					CTTcPr tcPr = ctTc.getTcPr();
					if (tcPr.isSetHMerge()) {
						CTHMerge hMerge = tcPr.getHMerge();
						if (STMerge.RESTART.equals(hMerge.getVal())) {
							cellContentList.add(getTableCellContent(cell));
						}
					} else if (tcPr.isSetVMerge()) {
						CTVMerge vMerge = tcPr.getVMerge();
						if (STMerge.RESTART.equals(vMerge.getVal())) {
							cellContentList.add(getTableCellContent(cell));
						}
					} else {
						cellContentList.add(getTableCellContent(cell));
					}
				}
			}
			tableContentList.add(cellContentList);
		}
		return tableContentList;
	}

	/**
	 * @Description: 得到表格内容,合并后的单元格视为一个单元格
	 */
	public List<List<String>> getTableContent(XWPFTable table) {
		List<List<String>> tableContentList = new ArrayList<List<String>>();
		for (int rowIndex = 0, rowLen = table.getNumberOfRows(); rowIndex < rowLen; rowIndex++) {
			XWPFTableRow row = table.getRow(rowIndex);
			List<String> cellContentList = new ArrayList<String>();
			for (int colIndex = 0, colLen = row.getTableCells().size(); colIndex < colLen; colIndex++) {
				XWPFTableCell cell = row.getCell(colIndex);
				cellContentList.add(getTableCellContent(cell));
			}
			tableContentList.add(cellContentList);
		}
		return tableContentList;
	}

	public String getTableCellContent(XWPFTableCell cell) {
		StringBuffer sb = new StringBuffer();
		List<XWPFParagraph> cellPList = cell.getParagraphs();
		if (cellPList != null && cellPList.size() > 0) {
			for (XWPFParagraph xwpfPr : cellPList) {
				List<XWPFRun> runs = xwpfPr.getRuns();
				if (runs != null && runs.size() > 0) {
					for (XWPFRun xwpfRun : runs) {
						sb.append(xwpfRun.getText(0));
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * @Description: 创建表格,创建后表格至少有1行1列,设置列宽
	 */
	public XWPFTable createTable(XWPFDocument xdoc, int rowSize, int cellSize, boolean isSetColWidth, int[] colWidths) {
		XWPFTable table = xdoc.createTable(rowSize, cellSize);
		if (isSetColWidth) {
			CTTbl ttbl = table.getCTTbl();
			CTTblGrid tblGrid = ttbl.addNewTblGrid();
			for (int j = 0, len = Math.min(cellSize, colWidths.length); j < len; j++) {
				CTTblGridCol gridCol = tblGrid.addNewGridCol();
				gridCol.setW(new BigInteger(String.valueOf(colWidths[j])));
			}
		}
		return table;
	}

	/**
	 * @Description: 设置表格总宽度与水平对齐方式
	 */
	public void setTableWidthAndHAlign(XWPFTable table, String width, STJc.Enum enumValue) {
		CTTblPr tblPr = getTableCTTblPr(table);
		CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
		if (enumValue != null) {
			CTJc cTJc = tblPr.addNewJc();
			cTJc.setVal(enumValue);
		}
		tblWidth.setW(new BigInteger(width));
		tblWidth.setType(STTblWidth.DXA);
	}

	/**
	 * @Description: 得到Table的CTTblPr,不存在则新建
	 */
	public CTTblPr getTableCTTblPr(XWPFTable table) {
		CTTbl ttbl = table.getCTTbl();
		CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
		return tblPr;
	}

	/**
	 * @Description: 得到Table的边框,不存在则新建
	 */
	public CTTblBorders getTableBorders(XWPFTable table) {
		CTTblPr tblPr = getTableCTTblPr(table);
		CTTblBorders tblBorders = tblPr.isSetTblBorders() ? tblPr.getTblBorders() : tblPr.addNewTblBorders();
		return tblBorders;
	}

	/**
	 * @Description: 设置表格边框样式
	 */
	public void setTableBorders(XWPFTable table, CTBorder left, CTBorder top, CTBorder right, CTBorder bottom) {
		CTTblBorders tblBorders = getTableBorders(table);
		if (left != null) {
			tblBorders.setLeft(left);
		}
		if (top != null) {
			tblBorders.setTop(top);
		}
		if (right != null) {
			tblBorders.setRight(right);
		}
		if (bottom != null) {
			tblBorders.setBottom(bottom);
		}
	}

	/**
	 * @Description: 表格指定位置插入一行, index为新增行所在的行位置(不能大于表行数)
	 */
	public void insertTableRowAtIndex(XWPFTable table, int index) {
		XWPFTableRow firstRow = table.getRow(0);
		XWPFTableRow row = table.insertNewTableRow(index);
		if (row == null) {
			return;
		}
		if (index >= 1 && table.getRow(index - 1) != null) {
			row.setHeight(table.getRow(index - 1).getHeight());
		}
		int cellSize = 0;
		cellSize = getCellSizeWithMergeNum(firstRow);
		for (int i = 0; i < cellSize; i++) {
			row.addNewTableCell();
		}
		// }
	}

	/**
	 * @Description: 删除表一行
	 */
	public void deleteTableRow(XWPFTable table, int index) {
		table.removeRow(index);
	}

	/**
	 * @Description: 统计列数(包括合并的列数)
	 */
	public int getCellSizeWithMergeNum(XWPFTableRow row) {
		List<XWPFTableCell> firstRowCellList = row.getTableCells();
		int cellSize = firstRowCellList.size();
		for (XWPFTableCell xwpfTableCell : firstRowCellList) {
			CTTc ctTc = xwpfTableCell.getCTTc();
			if (ctTc.isSetTcPr()) {
				CTTcPr tcPr = ctTc.getTcPr();
				if (tcPr.isSetGridSpan()) {
					CTDecimalNumber gridSpan = tcPr.getGridSpan();
					cellSize += gridSpan.getVal().intValue() - 1;
				}
			}
		}
		return cellSize;
	}

	/**
	 * @Description: 得到CTTrPr,不存在则新建
	 */
	public CTTrPr getRowCTTrPr(XWPFTableRow row) {
		CTRow ctRow = row.getCtRow();
		CTTrPr trPr = ctRow.isSetTrPr() ? ctRow.getTrPr() : ctRow.addNewTrPr();
		return trPr;
	}

	// /**
	// * @Description: 设置行高
	// */
	// public void setRowHeight(XWPFTableRow row, String hight,
	// STHeightRule.Enum heigthEnum) {
	// CTTrPr trPr = getRowCTTrPr(row);
	// CTHeight trHeight;
	// if (trPr.getTrHeightList() != null && trPr.getTrHeightList().size() > 0)
	// {
	// trHeight = trPr.getTrHeightList().get(0);
	// } else {
	// trHeight = trPr.addNewTrHeight();
	// }
	// trHeight.setVal(new BigInteger(hight));
	// if (heigthEnum != null) {
	// trHeight.setHRule(heigthEnum);
	// }
	// }

	/**
	 * @Description: 设置单元格内容
	 */
	public void setCellNewContent(XWPFTable table, int rowIndex, int col, String content) {
		XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
		XWPFParagraph p = getCellFirstParagraph(cell);
		XWPFTableCell tempCell = table.getRow(rowIndex).getCell(col);
		if (rowIndex != 0) {
			tempCell = table.getRow(rowIndex - 1).getCell(col);
		}
		XWPFParagraph tempP = getCellFirstParagraph(tempCell);
		XWPFRun tmpR = null;
		if (tempP.getRuns() != null && tempP.getRuns().size() > 0) {
			tmpR = tempP.getRuns().get(0);
		}
		List<XWPFRun> cellRunList = p.getRuns();
		for (int i = cellRunList.size() - 1; i > 0; i--) {
			p.removeRun(i);
		}
		XWPFRun newRun = p.createRun();
		newRun.setText(content);
		if (tmpR != null) {
			newRun.setBold(tmpR.isBold());
			newRun.setItalic(tmpR.isItalic());
			newRun.setUnderline(tmpR.getUnderline());
//			newRun.setColor(tmpR.getColor());
			newRun.setTextPosition(tmpR.getTextPosition());
			if (tmpR.getFontSize() != -1) {
				newRun.setFontSize(tmpR.getFontSize());
			}
			if (tmpR.getFontFamily() != null) {
				newRun.setFontFamily(tmpR.getFontFamily());
			}
			if (tmpR.getCTR() != null) {
				if (tmpR.getCTR().isSetRPr()) {
					CTRPr tmpRPr = tmpR.getCTR().getRPr();
					if (tmpRPr.isSetRFonts()) {
						CTFonts tmpFonts = tmpRPr.getRFonts();
						CTRPr newRunPr = newRun.getCTR().isSetRPr() ? newRun.getCTR().getRPr() : newRun.getCTR().addNewRPr();
						CTFonts cellFonts = newRunPr.isSetRFonts() ? newRunPr.getRFonts() : newRunPr.addNewRFonts();
						cellFonts.setAscii(tmpFonts.getAscii());
//						cellFonts.setAsciiTheme(tmpFonts.getAsciiTheme());
						cellFonts.setCs(tmpFonts.getCs());
//						cellFonts.setCstheme(tmpFonts.getCstheme());
						cellFonts.setEastAsia(tmpFonts.getEastAsia());
//						cellFonts.setEastAsiaTheme(tmpFonts.getEastAsiaTheme());
						cellFonts.setHAnsi(tmpFonts.getHAnsi());
//						cellFonts.setHAnsiTheme(tmpFonts.getHAnsiTheme());
					}
				}
			}
			if (cellRunList.size() == 2) {
				p.removeRun(0);
			}
			p.setAlignment(tempP.getAlignment());
			p.setBorderBetween(tempP.getBorderBetween());
			p.setBorderBottom(tempP.getBorderBottom());
			p.setBorderLeft(tempP.getBorderLeft());
			p.setBorderRight(tempP.getBorderRight());
			p.setBorderTop(tempP.getBorderTop());
			p.setPageBreak(tempP.isPageBreak());
			if (tempP.getCTP() != null) {
				if (tempP.getCTP().getPPr() != null) {
					CTPPr tmpPPr = tempP.getCTP().getPPr();
					CTPPr cellPPr = p.getCTP().getPPr() != null ? p.getCTP().getPPr() : p.getCTP().addNewPPr();
					// 复制段落间距信息
					CTSpacing tmpSpacing = tmpPPr.getSpacing();
					if (tmpSpacing != null) {
						CTSpacing cellSpacing = cellPPr.getSpacing() != null ? cellPPr.getSpacing() : cellPPr.addNewSpacing();
						if (tmpSpacing.getAfter() != null) {
							cellSpacing.setAfter(tmpSpacing.getAfter());
						}
						if (tmpSpacing.getAfterAutospacing() != null) {
							cellSpacing.setAfterAutospacing(tmpSpacing.getAfterAutospacing());
						}
						if (tmpSpacing.getAfterLines() != null) {
							cellSpacing.setAfterLines(tmpSpacing.getAfterLines());
						}
						if (tmpSpacing.getBefore() != null) {
							cellSpacing.setBefore(tmpSpacing.getBefore());
						}
						if (tmpSpacing.getBeforeAutospacing() != null) {
							cellSpacing.setBeforeAutospacing(tmpSpacing.getBeforeAutospacing());
						}
						if (tmpSpacing.getBeforeLines() != null) {
							cellSpacing.setBeforeLines(tmpSpacing.getBeforeLines());
						}
						if (tmpSpacing.getLine() != null) {
							cellSpacing.setLine(tmpSpacing.getLine());
						}
						if (tmpSpacing.getLineRule() != null) {
							cellSpacing.setLineRule(tmpSpacing.getLineRule());
						}
					}
					// 复制段落缩进信息
					CTInd tmpInd = tmpPPr.getInd();
					if (tmpInd != null) {
						CTInd cellInd = cellPPr.getInd() != null ? cellPPr.getInd() : cellPPr.addNewInd();
						if (tmpInd.getFirstLine() != null) {
							cellInd.setFirstLine(tmpInd.getFirstLine());
						}
						if (tmpInd.getFirstLineChars() != null) {
							cellInd.setFirstLineChars(tmpInd.getFirstLineChars());
						}
						if (tmpInd.getHanging() != null) {
							cellInd.setHanging(tmpInd.getHanging());
						}
						if (tmpInd.getHangingChars() != null) {
							cellInd.setHangingChars(tmpInd.getHangingChars());
						}
						if (tmpInd.getLeft() != null) {
							cellInd.setLeft(tmpInd.getLeft());
						}
						if (tmpInd.getLeftChars() != null) {
							cellInd.setLeftChars(tmpInd.getLeftChars());
						}
						if (tmpInd.getRight() != null) {
							cellInd.setRight(tmpInd.getRight());
						}
						if (tmpInd.getRightChars() != null) {
							cellInd.setRightChars(tmpInd.getRightChars());
						}
					}
				}
			}
		}
	}

	public void setCellNewContent(XWPFTable table, int rowIndex, int col, String content,int tempRowIndex){
		setCellNew(table,rowIndex,col,content,tempRowIndex,false,false);
	}

	public void setCellNewContentBold(XWPFTable table, int rowIndex, int col, String content,int tempRowIndex){
		setCellNew(table,rowIndex,col,content,tempRowIndex,true,false);
	}

    public void setCellNewContentTitleNotTemp(XWPFTable table, int rowIndex, int col, String content,int tempRowIndex){
        setCellNew(table,rowIndex,col,content,tempRowIndex,true,true);
    }
	public void setCellNewContentNotTemp(XWPFTable table, int rowIndex, int col, String content,int tempRowIndex){
		setCellNew(table,rowIndex,col,content,tempRowIndex,false,true);
	}
	/**
	 * @Description: 设置单元格内容
	 */
	public void setCellNew(XWPFTable table, int rowIndex, int col, String content,int tempRowIndex,boolean boldFlag,boolean tempFlag) {
		XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
		XWPFTableCell tempCell = table.getRow(tempRowIndex).getCell(col);
		if (rowIndex != tempRowIndex) {
			if(rowIndex == tempRowIndex + 1 || rowIndex==0){
				tempCell = table.getRow(rowIndex).getCell(col);
			}else{
			    if (tempFlag){
                    tempCell = table.getRow(rowIndex).getCell(col);
                }else {
                    tempCell = table.getRow(rowIndex - 2).getCell(col);
                }
			}
			if (tempCell != null && tempCell.getVerticalAlignment() != null) {
				cell.setVerticalAlignment(tempCell.getVerticalAlignment());
				if(StringUtils.isNotEmpty(tempCell.getColor())){
					cell.setColor(tempCell.getColor());
				}
			} else {
				cell.setVerticalAlignment(XWPFVertAlign.CENTER);
			}
		}else {
		    cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		}
		XWPFParagraph p = getCellFirstParagraph(cell);
		XWPFParagraph tempP = getCellFirstParagraph(tempCell);
		XWPFRun tmpR = null;
		if (tempP.getRuns() != null && tempP.getRuns().size() > 0) {
			tmpR = tempP.getRuns().get(0);
		}
		List<XWPFRun> cellRunList = p.getRuns();
		for (int i = cellRunList.size() - 1; i > 0; i--) {
			p.removeRun(i);
		}
		XWPFRun newRun = p.createRun();
		if(content.indexOf("\n") > 0){
			String[] text = content.split("\n");
			for (int f = 0; f < text.length; f++) {
				if (f == 0) {
					newRun.setText(text[f].trim());
				} else {
					// 换行
					newRun.addBreak();
					newRun.setText(text[f].trim());
				}
			}
		} else {
			newRun.setText(content);
		}
		newRun.setBold(boldFlag);
		newRun.setFontSize(10);
		newRun.setFontFamily("楷体");
		if (tmpR != null) {
			newRun.setBold(boldFlag || tmpR.isBold());
			newRun.setItalic(tmpR.isItalic());
			newRun.setUnderline(tmpR.getUnderline());
			newRun.setTextPosition(tmpR.getTextPosition());
			if (tmpR.getFontSize() != -1) {
				newRun.setFontSize(tmpR.getFontSize());
			}
			if (tmpR.getFontFamily() != null) {
				newRun.setFontFamily(tmpR.getFontFamily());
			}
			if (tmpR.getCTR() != null) {
				if (tmpR.getCTR().isSetRPr()) {
					CTRPr tmpRPr = tmpR.getCTR().getRPr();
					if (tmpRPr.isSetRFonts()) {
						CTFonts tmpFonts = tmpRPr.getRFonts();
						CTRPr newRunPr = newRun.getCTR().isSetRPr() ? newRun.getCTR().getRPr() : newRun.getCTR().addNewRPr();
						CTFonts cellFonts = newRunPr.isSetRFonts() ? newRunPr.getRFonts() : newRunPr.addNewRFonts();
						cellFonts.setAscii(tmpFonts.getAscii());
						cellFonts.setCs(tmpFonts.getCs());
						cellFonts.setEastAsia(tmpFonts.getEastAsia());
						cellFonts.setHAnsi(tmpFonts.getHAnsi());
					}
				}
			}
			if (cellRunList.size() == 2) {
				p.removeRun(0);
			}
			if (tempP.getCTP() != null) {
				if (tempP.getCTP().getPPr() != null) {
					CTPPr tmpPPr = tempP.getCTP().getPPr();
					CTPPr cellPPr = p.getCTP().getPPr() != null ? p.getCTP().getPPr() : p.getCTP().addNewPPr();
					// 复制段落间距信息
					CTSpacing tmpSpacing = tmpPPr.getSpacing();
					if (tmpSpacing != null) {
						CTSpacing cellSpacing = cellPPr.getSpacing() != null ? cellPPr.getSpacing() : cellPPr.addNewSpacing();
						if (tmpSpacing.getAfter() != null) {
							cellSpacing.setAfter(tmpSpacing.getAfter());
						}
						if (tmpSpacing.getAfterAutospacing() != null) {
							cellSpacing.setAfterAutospacing(tmpSpacing.getAfterAutospacing());
						}
						if (tmpSpacing.getAfterLines() != null) {
							cellSpacing.setAfterLines(tmpSpacing.getAfterLines());
						}
						if (tmpSpacing.getBefore() != null) {
							cellSpacing.setBefore(tmpSpacing.getBefore());
						}
						if (tmpSpacing.getBeforeAutospacing() != null) {
							cellSpacing.setBeforeAutospacing(tmpSpacing.getBeforeAutospacing());
						}
						if (tmpSpacing.getBeforeLines() != null) {
							cellSpacing.setBeforeLines(tmpSpacing.getBeforeLines());
						}
						if (tmpSpacing.getLine() != null) {
							cellSpacing.setLine(tmpSpacing.getLine());
						}
						if (tmpSpacing.getLineRule() != null) {
							cellSpacing.setLineRule(tmpSpacing.getLineRule());
						}
					}
					// 复制段落缩进信息
					CTInd tmpInd = tmpPPr.getInd();
					if (tmpInd != null) {
						CTInd cellInd = cellPPr.getInd() != null ? cellPPr.getInd() : cellPPr.addNewInd();
						if (tmpInd.getFirstLine() != null) {
							cellInd.setFirstLine(tmpInd.getFirstLine());
						}
						if (tmpInd.getFirstLineChars() != null) {
							cellInd.setFirstLineChars(tmpInd.getFirstLineChars());
						}
						if (tmpInd.getHanging() != null) {
							cellInd.setHanging(tmpInd.getHanging());
						}
						if (tmpInd.getHangingChars() != null) {
							cellInd.setHangingChars(tmpInd.getHangingChars());
						}
						if (tmpInd.getLeft() != null) {
							cellInd.setLeft(tmpInd.getLeft());
						}
						if (tmpInd.getLeftChars() != null) {
							cellInd.setLeftChars(tmpInd.getLeftChars());
						}
						if (tmpInd.getRight() != null) {
							cellInd.setRight(tmpInd.getRight());
						}
						if (tmpInd.getRightChars() != null) {
							cellInd.setRightChars(tmpInd.getRightChars());
						}
					}
				}
			}
			if(tempCell.getCTTc().getTcPr().getTcBorders() != null && tempCell.getCTTc().getTcPr().getTcBorders().getRight() != null
					&& tempCell.getCTTc().getTcPr().getTcBorders().getRight().getVal() != STBorder.NIL){
				CTTcBorders tblBorders = cell.getCTTc().getTcPr().addNewTcBorders();
				CTBorder cTBorder = tblBorders.addNewRight();
				cTBorder.setVal(tempCell.getCTTc().getTcPr().getTcBorders().getRight().getVal());
				cTBorder.setColor(tempCell.getCTTc().getTcPr().getTcBorders().getRight().getColor());
			}
			p.setBorderBetween(tempP.getBorderBetween());
			p.setBorderBottom(tempP.getBorderBottom());
			p.setBorderLeft(tempP.getBorderLeft());
			//p.setBorderRight(tempP.getBorderRight());
			p.setBorderTop(tempP.getBorderTop());
			p.setAlignment(tempP.getAlignment());
			p.setPageBreak(tempP.isPageBreak());
		}
	}

	/**
	 * @Description: 设置单元格内容
	 */
	public void setCellNewContentTemp00(XWPFTable table, int rowIndex, int col, String content) {
		XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
		XWPFParagraph p = getCellFirstParagraph(cell);
		XWPFTableCell tempCell = table.getRow(0).getCell(0);
		XWPFParagraph tempP = getCellFirstParagraph(tempCell);
		XWPFRun tmpR = null;
		if (tempP.getRuns() != null && tempP.getRuns().size() > 0) {
			tmpR = tempP.getRuns().get(0);
		}
		List<XWPFRun> cellRunList = p.getRuns();
		for (int i = cellRunList.size() - 1; i > 0; i--) {
			p.removeRun(i);
		}
		XWPFRun newRun = p.createRun();
		newRun.setText(content);
		if (tmpR != null) {
			newRun.setBold(tmpR.isBold());
			newRun.setItalic(tmpR.isItalic());
			newRun.setUnderline(tmpR.getUnderline());
//			newRun.setColor(tmpR.getColor());
			newRun.setTextPosition(tmpR.getTextPosition());
			if (tmpR.getFontSize() != -1) {
				newRun.setFontSize(tmpR.getFontSize());
			}
			if (tmpR.getFontFamily() != null) {
				newRun.setFontFamily(tmpR.getFontFamily());
			}
			if (tmpR.getCTR() != null) {
				if (tmpR.getCTR().isSetRPr()) {
					CTRPr tmpRPr = tmpR.getCTR().getRPr();
					if (tmpRPr.isSetRFonts()) {
						CTFonts tmpFonts = tmpRPr.getRFonts();
						CTRPr newRunPr = newRun.getCTR().isSetRPr() ? newRun.getCTR().getRPr() : newRun.getCTR().addNewRPr();
						CTFonts cellFonts = newRunPr.isSetRFonts() ? newRunPr.getRFonts() : newRunPr.addNewRFonts();
						cellFonts.setAscii(tmpFonts.getAscii());
//						cellFonts.setAsciiTheme(tmpFonts.getAsciiTheme());
						cellFonts.setCs(tmpFonts.getCs());
//						cellFonts.setCstheme(tmpFonts.getCstheme());
						cellFonts.setEastAsia(tmpFonts.getEastAsia());
//						cellFonts.setEastAsiaTheme(tmpFonts.getEastAsiaTheme());
						cellFonts.setHAnsi(tmpFonts.getHAnsi());
//						cellFonts.setHAnsiTheme(tmpFonts.getHAnsiTheme());
					}
				}
			}
			if (cellRunList.size() == 2) {
				p.removeRun(0);
			}
			p.setAlignment(tempP.getAlignment());
			p.setBorderBetween(tempP.getBorderBetween());
			p.setBorderBottom(tempP.getBorderBottom());
			p.setBorderLeft(tempP.getBorderLeft());
			p.setBorderRight(tempP.getBorderRight());
			p.setBorderTop(tempP.getBorderTop());
			p.setPageBreak(tempP.isPageBreak());
			if (tempP.getCTP() != null) {
				if (tempP.getCTP().getPPr() != null) {
					CTPPr tmpPPr = tempP.getCTP().getPPr();
					CTPPr cellPPr = p.getCTP().getPPr() != null ? p.getCTP().getPPr() : p.getCTP().addNewPPr();
					// 复制段落间距信息
					CTSpacing tmpSpacing = tmpPPr.getSpacing();
					if (tmpSpacing != null) {
						CTSpacing cellSpacing = cellPPr.getSpacing() != null ? cellPPr.getSpacing() : cellPPr.addNewSpacing();
						if (tmpSpacing.getAfter() != null) {
							cellSpacing.setAfter(tmpSpacing.getAfter());
						}
						if (tmpSpacing.getAfterAutospacing() != null) {
							cellSpacing.setAfterAutospacing(tmpSpacing.getAfterAutospacing());
						}
						if (tmpSpacing.getAfterLines() != null) {
							cellSpacing.setAfterLines(tmpSpacing.getAfterLines());
						}
						if (tmpSpacing.getBefore() != null) {
							cellSpacing.setBefore(tmpSpacing.getBefore());
						}
						if (tmpSpacing.getBeforeAutospacing() != null) {
							cellSpacing.setBeforeAutospacing(tmpSpacing.getBeforeAutospacing());
						}
						if (tmpSpacing.getBeforeLines() != null) {
							cellSpacing.setBeforeLines(tmpSpacing.getBeforeLines());
						}
						if (tmpSpacing.getLine() != null) {
							cellSpacing.setLine(tmpSpacing.getLine());
						}
						if (tmpSpacing.getLineRule() != null) {
							cellSpacing.setLineRule(tmpSpacing.getLineRule());
						}
					}
					// 复制段落缩进信息
					CTInd tmpInd = tmpPPr.getInd();
					if (tmpInd != null) {
						CTInd cellInd = cellPPr.getInd() != null ? cellPPr.getInd() : cellPPr.addNewInd();
						if (tmpInd.getFirstLine() != null) {
							cellInd.setFirstLine(tmpInd.getFirstLine());
						}
						if (tmpInd.getFirstLineChars() != null) {
							cellInd.setFirstLineChars(tmpInd.getFirstLineChars());
						}
						if (tmpInd.getHanging() != null) {
							cellInd.setHanging(tmpInd.getHanging());
						}
						if (tmpInd.getHangingChars() != null) {
							cellInd.setHangingChars(tmpInd.getHangingChars());
						}
						if (tmpInd.getLeft() != null) {
							cellInd.setLeft(tmpInd.getLeft());
						}
						if (tmpInd.getLeftChars() != null) {
							cellInd.setLeftChars(tmpInd.getLeftChars());
						}
						if (tmpInd.getRight() != null) {
							cellInd.setRight(tmpInd.getRight());
						}
						if (tmpInd.getRightChars() != null) {
							cellInd.setRightChars(tmpInd.getRightChars());
						}
					}
				}
			}
		}
	}

	/**
	 * @Description: 删除单元格内容
	 */
	public void deleteCellContent(XWPFTable table, int rowIndex, int col) {
		XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
		XWPFParagraph p = getCellFirstParagraph(cell);
		List<XWPFRun> cellRunList = p.getRuns();
		if (cellRunList == null || cellRunList.size() == 0) {
			return;
		}
		for (int i = cellRunList.size() - 1; i >= 0; i--) {
			p.removeRun(i);
		}
	}

	/**
	 * 
	 * @Description: 得到Cell的CTTcPr,不存在则新建
	 */
	public CTTcPr getCellCTTcPr(XWPFTableCell cell) {
		CTTc cttc = cell.getCTTc();
		CTTcPr tcPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
		return tcPr;
	}

	/**
	 * @Description: 设置垂直对齐方式
	 */
	public void setCellVAlign(XWPFTableCell cell, STVerticalJc.Enum vAlign) {
		setCellWidthAndVAlign(cell, null, null, vAlign);
	}

	/**
	 * @Description: 设置列宽和垂直对齐方式
	 */
	public void setCellWidthAndVAlign(XWPFTableCell cell, String width, STTblWidth.Enum typeEnum, STVerticalJc.Enum vAlign) {
		CTTcPr tcPr = getCellCTTcPr(cell);
		CTTblWidth tcw = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
		if (width != null) {
			tcw.setW(new BigInteger(width));
		}
		if (typeEnum != null) {
			tcw.setType(typeEnum);
		}
		if (vAlign != null) {
			CTVerticalJc vJc = tcPr.isSetVAlign() ? tcPr.getVAlign() : tcPr.addNewVAlign();
			vJc.setVal(vAlign);
		}
	}

	/**
	 * @Description: 得到单元格第一个Paragraph
	 */
	public XWPFParagraph getCellFirstParagraph(XWPFTableCell cell) {
		XWPFParagraph p;
		if (cell.getParagraphs() != null && cell.getParagraphs().size() > 0) {
			p = cell.getParagraphs().get(0);
		} else {
			p = cell.addParagraph();
		}
		return p;
	}

	/**
	 * @Description: 得到单元格第一个Paragraph
	 */
	public void replaceTableCell(XWPFTableCell cell,String text) {
		XWPFParagraph p = getCellFirstParagraph(cell);
		p.getRuns().get(0).setText(text,0);
	}

	/**
	 * @Description: 跨列合并
	 */
	public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
		for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
			XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
			if (cellIndex == fromCell) {
				// The first merged cell is set with RESTART merge value
				getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one,are set with CONTINUE
				getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.CONTINUE);
			}
		}
	}

	/**
	 * @Description: 跨行合并
	 * @see https://stackoverflow.com/questions/24907541/row-span-with-xwpftable
	 */
	public void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			if (rowIndex == fromRow) {
				// The first merged cell is set with RESTART merge value
				getCellCTTcPr(cell).addNewVMerge().setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one,are set with CONTINUE
				getCellCTTcPr(cell).addNewVMerge().setVal(STMerge.CONTINUE);
			}
		}
	}

	public void setHyperlink(XWPFParagraph para,String url,String text){
		String id = para.getDocument().getPackagePart()
				.addExternalRelationship(url, XWPFRelation.HYPERLINK.getRelation()).getId();
		// Append the link and bind it to the relationship
		CTHyperlink cLink = para.getCTP().addNewHyperlink();
		cLink.setId(id);
		// Create the linked text
		CTText ctText = CTText.Factory.newInstance();
		ctText.setStringValue(text);
		CTR ctr = CTR.Factory.newInstance();
		CTRPr rpr = ctr.addNewRPr();
		// 设置超链接样式
		CTColor color = CTColor.Factory.newInstance();
		color.setVal("0000FF");
		rpr.setColor(color);
		rpr.addNewU().setVal(STUnderline.NONE);
		// 设置字体
		CTFonts fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
		fonts.setAscii("楷体");
		fonts.setEastAsia("楷体");
		fonts.setHAnsi("楷体");
		// 设置字体大小
		CTHpsMeasure sz = rpr.isSetSz() ? rpr.getSz() : rpr.addNewSz();
		sz.setVal(new BigInteger("20"));
		ctr.setTArray(new CTText[] { ctText });
		// Insert the linked text into the link
		cLink.setRArray(new CTR[] { ctr });
	}

	public void clearParagraph(XWPFParagraph paragraph){
		List<XWPFRun> runs = paragraph.getRuns();
		for(int a = 0;a < runs.size();a++){
			runs.get(a).setText("",0);
		}
	}

	public void setRunText(XWPFRun run,String text){
		run.setText(text);
		run.setFontSize(12);
		run.setFontFamily("楷体");
	}

	public void setNewsFormRunText(XWPFRun run,String text){
		run.setText(text);
		run.setFontSize(9);
		run.setFontFamily("楷体");
	}
}
