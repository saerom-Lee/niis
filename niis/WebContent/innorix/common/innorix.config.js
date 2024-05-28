/*!
 * @file INNORIX Config JS
 * @name config
 */
;(function(window, document) {

    "use strict";

	//최종라이센스_180720
    var license = "O3lGRvn1C1PF2VwgUwMQ4S0CWuT0IIPg7fOffPccHEpZS5Yxx9YriT0gKEthkQDrdMSlduVycIOZPA8xEUuMCgpGAZy+BpqT2T146bSlxjPFqnGpTmeQuxAFafg=";
	//var license = "JFup5GZdzM5Jh8d+pbdmz5k0StvXYylIQ6BZLyv8xxtNeaprYyNw433TokkhyoRA6rv3ttpkKOlXK2K0LgvwTeS0w/dkSQpzWuLOY/s9iHqkhWHRaXDM3dzcT8aFlqyFwnV7uQ==";
	//var license = "rk/vm2ASZvexBA9G0ism7YcXGuLlFUGUqkdSo5RCx9/gBgroaWdSzX+rI82EyqbYatwAquJZIo9JBvKemuHLfJti4QtnAwFGletmbPfvYr8vPJjorvmo390bInU=";	// 라이선스 유형 : InnoEX(Upload, Downlaod) 31일 체험 (만료 2018-02-12)

	//var license = "JF1tyq/WZL07ZF7e1RTlzTqO+Dy/SSlEBkocDkbWYP+wOfaXRfYNWYbfpRER7piYJ47HZImwevKZ6RMSVzwAK+IJnxfotu7A54uJ3tY4sOJefkxWd0fpqNWrs1/9u3cmi8VmNw==";// 라이선스 유형 : InnoEX(Upload, Downlaod) 정식  // http://image.ngii.co.kr

    var versionWindows = "8,1,3,111";
    var versionMacOS = "8,1,3,98";
    var versionLinux = "8,1,3,98";
    
    //var baseURL = "../../common";
    var baseURL = location.protocol + "//" + location.hostname + (location.port && ":" + location.port) + "/niis/innorix/common";

    /*  
        중요사항: 업데이트, 기술지원, 유지보수를 위해 아래 디렉토리를 유지 하십시오.
       
        디렉토리 구성정보:
            baseURL + /devTool   : 디버그 리소스
            baseURL + /control   : 컨트롤 리소스
            baseURL + /trnWindow : 전송창 리소스
            baseURL + /install   : 설치안내 리소스
            baseURL + /package   : 컨트롤 설치파일
    */
    var innorix, innoConfig, filePath;

    function isInstallHtml() {
        var scriptTags, i, len, src, patt, debug;
        scriptTags = document.getElementsByTagName("script");
        for(i = 0, len = scriptTags.length; i < len; i++) {
            src = scriptTags[i].src;
            patt = /innorixInstallCheck\s*=\s*true/;
            if(patt.test(src)) {
                debug = innoConfig.debug;
                debug.console = false;
                debug.devTool = false;
            }
        }
    }

    innorix = window.innorix = {};

    innoConfig = innorix.config = {
        debug: {
            console: false,
            devTool: false,
            devToolHtmlUrl: baseURL + "/devTool/innorix_devTool_kr.compressed.html"
        },

        controlMode: 0,

        control: {
            charset: "utf-8",
            duplicateFile: "confirm",
            width: 550,
            height: 200,
            downloadType: "direct",
            contextMenu: true,
            dropZone: true,            
            tooltipSize: {width: 320},
            autoStart: false,
            extraView: "show",
            htmlTransferWindow: true,
            htmlTransferWindowUrl: baseURL + "/trnWindow/innorix_trnWindow_type_lang.compressed.html",
            htmlTransferWindowType: "normal",
            imgSrc: baseURL + "/control/img",
            workerUrl: baseURL + "/control/innorix.controlWorker.compressed.js"
        },

        install: {
            version: {
                Windows: versionWindows,
                Mac: versionMacOS,
                Linux: versionLinux
            },
            clsid: "F6730686-F2B7-42c9-8FD6-E18A78A97A42",
            mimeType: "application/innorix-file-transfer-solution-g81",
            installFileWindows: baseURL + "/package/innogmp_win.exe",
            installFileMacOS: baseURL + "/package/innogmp_mac.pkg",
            installFileLinux: baseURL + "/package/innogmp_lin.deb"
        },

        plugin: {
            indiedialog: true,
            license: license,
            language: "kr",
            addFileAfterRefresh: 2,
            usecontinuetransfer: 1,
            uploadretrycount: 2147483647,
            uploadretrysec: 3,
            downloadretrycount: 2147483647,
            downloadretrysec: 3,            
            autoSetting: true,
            loglevel: 7,
            //multiFileCount: 32,
            //multiSessionCount: 32,
            //uploadDataSize: 262144,
            //downloadDataSize: 262144,
            scriptrevision: "1.0.0",
            mftserverurl: ""
        }
    };

    filePath = innoConfig.filePath = {
        requireJS: baseURL + "/control/innorix.require.compressed.js",

        base: [
            [
                baseURL + "/control/innorix.control.compressed.css"
            ],
            [
                baseURL + "/control/i18n/innorix.control.locale-" + innoConfig.plugin.language + ".js",
                baseURL + "/control/innorix.control.p.compressed.js"
            ]
        ],

        noPlugin: [
            [
                baseURL + "/control/innorix.control.compressed.css"
            ],
            [
                baseURL + "/control/i18n/innorix.control.locale-" + innoConfig.plugin.language + ".js",
                baseURL + "/control/innorix.control.np.compressed.js"
            ]
        ]
    };

    isInstallHtml();

    innorix.message = {};

    document.writeln("<script type=\"text/javascript\" src=\"" + filePath.requireJS + "\"></script>");

}(window, document));