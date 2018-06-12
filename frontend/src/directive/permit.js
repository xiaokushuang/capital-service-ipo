import Vue from "vue";
import * as auth from '@/utils/auth'

const permit = Vue.directive('permit', {
  bind: function (el, binding) {
  },
  inserted: function (el, binding) {
    let permissions = [];
    let permsJson = el.getAttribute('perms');
    if (permsJson) {
      permissions = JSON.parse(permsJson);
      let result = allPermitted(permissions);
      console.log('all permitted > ', el, permissions, getPermissions(),  result);
      if (!result) {
        el.remove();
      }
      return;
    }
    permsJson = el.getAttribute('anyPerms');
    if (permsJson) {
      permissions = JSON.parse(permsJson);
      let result = anyPermitted(permissions);
      console.log('any permitted > ', el, permissions, getPermissions(), result);
      if (!result) {
        el.remove();
      }
      return;
    }
  },
  unbind: function (el, binding) {
  }
});

let allPermitted = function (_permissions) {
  let _hasPermsArray = [];
  for (let index = 0; index < _permissions.length; index++) {
    if (isPermitted(_permissions[index])) {
      _hasPermsArray.push(_permissions[index]);
    }
  }
  return _hasPermsArray.length == _permissions.length;
};


let anyPermitted = function (_permissions) {
  for (let index = 0; index < _permissions.length; index++) {
    if (isPermitted(_permissions[index])) {
      return true;
    }
  }
  return false;
};

let isPermitted = function (_permit) {
  let _permissions = getPermissions();
  for (let index = 0; index < _permissions.length; index++) {
    let _code = _permissions[index].resourceName;
    if (_code == _permit) {
      return true;
    }
  }
  return false;
};


let getPermissions = function(){
  return JSON.parse(auth.getPermissions());
}

export default permit;