package com.stock.capital.enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.stock")
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
        com.spire.license.LicenseProvider.setLicenseKey("QIXZTXGAeeY6WfjGlwEAF5IPGqXGg44VrEq7xpwMZXgKpCj7W1GRfZ8pXEpEc8gghPO+wwYMIs0ui1SVyG4aaIEHNQMifnBr254Whf/sDUXjjQC5sk/9zhv0yBEW04oPW4WlatF3WPBFBxFzLaGJfrJ7Wh1NuMMSE44KsAqIaDep9iCw2qCNnof6mwviF2MMD2Nxv+GsL3R3ASkpDnSQXl0OV1xBmo6mHre4ocbFJ2xW3MnH5AuRa9qkeeyVOw4krIo55u2cozvXO2K0CIluINCelkIvddvaQIEbKPNDt+/sqaZYSICli5jt82pYgvUgVUi+HhmmQ/1oVBG2ii9Z5cyLswSj6LH/UJBgPVZ87Qhr0d4uqlXVS7cvmAzt1drVGulIJ7704jryTxOcMJfxURLf6lC0QskuZ9sLCgRfWQyZVjus0Scxg3hCmlmkiysFvnbgLWjc/nfvCjFhX3ZcAGxPiPZ7eymP2EdCnsYQ4dLFIzTYXjbfGvIygLHSd9yF5SNRh3notk7h8JO+C/hRUat0gfAm9PQDnZMV+efMkfelpm/QKxzzcSOomE10p6UAev/9aZbPpBVJZC7T05EALgVWhoth8OFkY8RDolEhd5SggXVl1+jkSYTJmOr2mgWedxhFK6pmwH8tWz8P8oPnQYvciAhSG3gWpm8xigZUh9Nid0Syc64Xnsah+iV4faA013pLqe70RgIeaTJTm7h2+EeDDL7a/6PIIbxSpvx8Zkdmn0Jcyu13/7EL7CEQkwes6Nhd8SiG3RFjHXjSafkwp/J6OvbGR7qFI9rKfPmTQX4p5E0cxAu9D7YqfWQt87YBw+Toff0bayViKJaeqceBA/g3tp2+qQN88dPwID1E8hCj57kGtaCDawpg6zkYYxbaNbyIlmsHH1OhExk7MLBALEfBiVXdaQFdNrNSxyxcG/r1R1UoQWuVlq25LiKlbxs49lWXKAtluGRxh+HmyxZrIUnJY3dTXJiuZm26lqjn98QZafHxrXSqSuzL6tkKZLFtlKxZ7njUQ2ySwDDvVVgaEiQ4FORfxX1gGWjdib9fhuLKikyLAvYIX0IpsCjL7uiNG6OuVwA0kPUW7JN27ltFSpReu9yqY42oWJH7hrZTiTDuvcqZk4QqXVfNRl/FXWB/l9grCY51UZQ+uZindCIBcy4fEjBzc3ft4qhlFdkotbReUwwR6tSKYJec0scd8tlndMOZmGNY7Ryix2Ebp61p8juEHchPlE9P2bB969JiOS7NvJPZuDubBQNSIXibOBZiRoH135Y4xS3WUagC41ljmXFVQKf1eFl1Z4BGa1fpCCQfN6a2gncST+EenSrnh+Eej9xumJQz8PCjlnBvhy9dUfdNmZB9dBHNt+VraVjLGXInPL7VY0RzyXf/BdRsVUkSLSub8DAu0nT8fxIx9PTNJGpx3pEbPn9wmSKTSJ+Z/5tejuFU53KbUl89/Uf9YMedn2ug2egZsHpmZ3oB1ZUQdk4YkukKSix2TOiUh/R7+kDWGFaVprAKDrj8dlCuL7DBRO/oHuIigNmF4jjwSGy2ahtkELthq0Rg2V5J2jhbebYeNDQxkSzzUqLwo4/fS7YznVUyMbiZmOA=");
//        Spire.License.LicenseProvider.SetLicenseFileName("license1.elic.xml");
//        Spire.License.LicenseProvider.LoadLicense();
    }

}