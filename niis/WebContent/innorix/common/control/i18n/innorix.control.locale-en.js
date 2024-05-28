/*!
 * @file INNORIX Control Lang EN JS
 * @name langEnglish
 */
(function(innorix) {
    "use strict";
    innorix.message = {
        error: {
            cannotFileExt: "*.$arg1 file can not be attached.",
            fileDuplicate: "The same file is already attached.",
            overFileCount: "The maximum number of files is $arg1.",
            overFileSize: "The maximum limit of a single file is $arg1.",
            overTotalSize: "The maximum limit of total files is $arg1",
            zeroSizeFile: "",
            selectOneFile: "Please select a file to open.",
            trWinIsNotValid: "Transfer window URL does not exist."
        },
        dialog: {
            confirmAddUpResumeFiles: "There was a interruption. \n Do you want to resume transfer?",
            confirmUpResume: "Do you want to resume uploading?",
            alertChangeToMassControl: "UI will be changed to mass file upload mode \n because more than 100 files are attached."
        },
        control: {
            andFiles: "and $arg1 file(s)",
            chooseFile: "Choose File",
            fileDownload: "File Download",
            unit: " file(s)",
            noFile: "File does not exist",
            addingFiles: "Files are being attached",
            readyForTransfer: "Mass files transfer optimization is completed.",
            prepareForTransfer: "Preparing for file transfer",
            eta: "Estimated time"
        },
        controlHeader: {
            removeAll: "Remove all files",
            removeSelected: "Remove the selected file(s)",
            downAll: "Down all files",
            selected: "The selected file(s)",
            remove: "Remove",
            open: "Open",
            down: "Download"
        },
        controlInfo: {
            noLimit: "Unlimited",
            noFileTypeLimit: "All types of file",
            fileTypeLimit: "File types",
            capacity: "Capacity",
            count: "",
            unit: " files",
            resumeUp: "Resume uploading",
            autoRecov: "Auto recovery",
            bigSize: "Large file transfer",
            massFile: "Mass file transfer",
            smartDz: "Smart drop-zone",
            multiDz: "Multi drop-zones",
            secureFile: "Secure file transfer",
            smartZip: "Smart compression",
            express: "High-speed transfer",
            upSpeedCheck: "Upload speed check",
            downSpeedCheck: "Download speed check"
        },
        contextMenu: {
            addFile: "Attach new file(s)",
            addFolder: "Attach new folder(s)",
            removeSelect: "Remove selected file(s)",
            removeAll: "Remove all files",
            executeFile: "Open file",
            upload: "Upload all files",
            download: "Download all files",
            downloadSelected: "Download selected file(s)",
            openFile: "Open file",
            showAttrs: "View attributes"
        },
        controlExtraView: {
            item: "item",
            items: "items",
            attached: "attached",
            name: "Name",
            size: "Size",
            type: "Type",
            upload: "Upload",
            download: "Download",
            creationTime: "Date created",
            modifiedTime: "Date Modified",
            pageCount: "Page count",
            author: "Author",
            resolution: "Resolution",
            selected: "Selected",
            fileAttached: " file(s) attached",
            fileSelected: " file(s) selected",
            fileTotalSelInfo: "($arg2 of $arg1 file(s) selected)",
            estimatedTime: "Estimated time",
            total: "Total",
            hour: "hour",
            min: "min",
            sec: "sec",
            delFile: "Cancel"
        },
        tooltip: {
            unlimited: "unlimited",
            possibleCapacity: "Maximum file size",
            fileAttached: " file(s) $arg1 attached",
            fileCount: "# of file(s)",
            size: "Size",
            unit: "",
            name: "Name",
            type: "Type",
            creationTime: "Date created",
            modifiedTime: "Date modified",
            path: "Path",
            imgSize: "Image size",
            fNumber: "F-number",
            iso: "ISO speed",
            exposureTime: "Exposure time",
            exposureBias: "Exposure bias",
            datePicTaken: "Date picture taken",
            cameraModel: "Camera model",
            author: "Author",
            pageCount: "Page",
            version: "Version",
            revisionCount: "Revision",
            showImg: "Preview",
            showAttr: "View attributes"
        },
        plugin: {
            ID_ASK_FILENAME: "'%s' is already in the same folder.",
            ID_ASK_EXIST: "Existing file",
            ID_ASK_NEWFILESIZE: "New file size is %s.",
            ID_ASK_ALLCHECK: "Apply this option to all files",
            ID_ASK_BTN_OVERWRITE: "Overwrite",
            ID_ASK_BTN_RESUME: "Resume",
            ID_ASK_BTN_SAVEAS: "Save as...",
            ID_ASK_BTN_CANCEL: "Cancel",
            ID_ASK_BTN_JUMP: "Skip",
            ID_ASK_BTN_SAVEAUTO: "Auto-rename",
            ID_ASK_TEXT_AUTONAME_TAIL: "It will be saved as %s",
            ID_ASK_ALLCHECK_NEW: "Apply this for the next %d conflicts",
            ID_ASK_SUN: "Sunday",
            ID_ASK_MON: "Monday",
            ID_ASK_TUE: "Tuesday",
            ID_ASK_WED: "Wednesday",
            ID_ASK_THU: "Thursday",
            ID_ASK_FRI: "Friday",
            ID_ASK_SAT: "Saturday",
            ID_ASK_AM: "AM",
            ID_ASK_PM: "PM",
            ID_ASK_JANUARY: "January",
            ID_ASK_FEBRUARY: "February",
            ID_ASK_MARCH: "March",
            ID_ASK_APRIL: "April",
            ID_ASK_MAY: "May",
            ID_ASK_JUNE: "June",
            ID_ASK_JULY: "July",
            ID_ASK_AUGUST: "August",
            ID_ASK_SEPTEMBER: "September",
            ID_ASK_OCTOBER: "October",
            ID_ASK_NOVEMBER: "November",
            ID_ASK_DECEMBER: "December",
            ID_FD_ASK_FILE: "File",
            ID_FD_ASK_BTN_OPEN: "Open",
            ID_FD_ASK_BTN_SAVE: "Save",
            ID_FD_ASK_BTN_SAVEOPEN: "Open after saving",
            ID_FD_ASK_MSG: "Do you want to save and open this file?",
            ID_FD_ASK_TARGET: "Target file",
            ID_FD_ASK_NAME: "File name",
            ID_FD_ASK_TYPE: "File type",
            ID_FD_ASK_ALLFILES: "All files",
            ID_FD_ASK_SAVEAS: "Save as...",
            ID_ITEM_CANCEL: "Cancel",
            ID_ITEM_DONE: "Complete",
            ID_ITEM_DONEING: "Completing",
            ID_ITEM_PAUSE: "Pause",
            ID_ITEM_FAILED: "Error",
            ID_ITEM_TRANSFER_DOWNLOAD: "Downloading",
            ID_ITEM_TRANSFER: "Uploading",
            ID_MSG_LICENSE_INVALID: "Please enter license key.",
            ID_MSG_LICENCE_UNAUTH: "The current domain does not match the license key.",
            ID_MSG_LICENCE_END: "Trial version has expired.",
            ID_MSG_MODE_INVALID: "The selected mode does not match the license key.",
            ID_MSG_LANGUAGE_SET_INVALID: "The selected language is not supported in this license key.",
            ID_MSG_PLATFORM_SET_INVALID: "The selected platform is not supported in this license key.",
            ID_MSG_ACCESS_DENIED: "Access denied for the selected file.",
            ID_MSG_ALREADY_EXISTS: "The same file has already been attached.",
            ID_MSG_EXECUTE_FAIL: "This file type does not have default program.",
            ID_MSG_FREE_SPACE_NOT_ENOUGH: "There is not enough free disk space.",
            ID_MSG_TRANSFER_COMPLETE: "File transfer is completed.",
            ID_MSG_TRANSFER_WINDOW_ISNOT_VAILD: "Transfer window URL does not exist.",
            ID_STR_FILENAME: "Name",
            ID_STR_FILESIZE: "Size",
            ID_STR_STATUS: "Status",
            ID_STR_NERWORK_ERROR: "Exception has occurred when connecting to the network.",
            ID_STR_TRANSFER_RETRY: "Retry after %d sec.",
            ID_DLG_BTN_START: "Start",
            ID_DLG_BTN_PAUSE: "Pause",
            ID_DLG_BTN_FOLDER_CHANGE: "Change path",
            ID_DLG_BTN_REMOVE_SELECTED: "Remove",
            ID_DLG_BTN_UP_ICON: "↑",
            ID_DLG_BTN_DOWN_ICON: "↓",
            ID_DLG_BTN_OK: "OK",
            ID_DLG_BTN_TRANSFER_CANCEL: "Cancel",
            ID_DLG_BTN_TRANSFER_RETRY: "Retry",
            ID_DLG_STR_ALERT: "Notice",
            ID_DLG_STR_TITLE_DS: "UPLOAD - INNODS",
            ID_DLG_STR_TITLE_AP: "UPLOAD - INNOAP",
            ID_DLG_STR_TITLE_FD: "DOWNLOAD - INNOFD",
            ID_DLG_STR_TITLE_EX: "UPLOAD - INNOEX",
            ID_DLG_STR_TITLE_EX1: "DOWNLOAD - INNOEX",
            ID_DLG_STR_TITLE_FD_PLUS: "DOWNLOAD - INNOFD Plus",
            ID_DLG_STR_TITLE_DS_PLUS: "UPLOAD - INNODS Plus",
            ID_DLG_STR_FD_INIT: 'Click the "Start" button to download.',
            ID_DLG_STR_DS_INIT: 'Click the "Start" button to upload.',
            ID_DLG_STR_CURRENT_FILES: "Current file :",
            ID_DLG_STR_TRANSFER_RATE: "Transfer rate :",
            ID_DLG_STR_PROGRESS: "Progress :",
            ID_DLG_STR_TIME_LEFT: "left",
            ID_DLG_STR_STATUS: "Status :",
            ID_DLG_STR_SAVE_PATH: "Save path :",
            ID_DLG_STR_TIMER_SEC: "sec",
            ID_DLG_STR_TIMER_MIN: "min",
            ID_DLG_STR_TIMER_HR: "",
            ID_DLG_STR_TIMER_HR1: "hr",
            ID_DLG_STR_DOWN_AND_OPEN_FOLDER: "Open folder",
            ID_DLG_STR_WAIT: "Wait",
            ID_DLG_STR_FILE_SEND: "Files are being transferred.",
            ID_DLG_STR_TRANSFER_PAUSE: "Transfer has paused.",
            ID_DLG_STR_FILE_CANCEL: "Please wait. File transfer is being canceled.",
            ID_DLG_STR_FILE_WAIT: "Waiting for responses from the server.",
            ID_TRAY_STR_OPEN_DS: "Open the upload status window.",
            ID_TRAY_STR_OPEN_FD: "Open the download status window.",
            ID_TRAY_STR_MINIMIZE: "Minimize to tray",
            ID_TRAY_STR_EXIT: "Quit",
            ID_TRAY_STR_OPEN_LOGFOLDER: "Open the log folder.",
            ID_CLOSE_DLG_MSG_FD: "Are you sure you want to stop downloading?",
            ID_CLOSE_DLG_MSG_DS: "Are you sure you want to stop uploading?",
            ID_CLOSE_DLG_BTN_QUIT: "Quit",
            ID_CLOSE_DLG_BTN_MINIMIZE: "Minimize to tray",
            ID_CLOSE_DLG_BTN_CANCEL: "Cancel",
            ID_ERRTITLE_GET_FILEINFO_FAIL: "Exception has occurred while checking file.",
            ID_ERRTITLE_FILE_DOWNLOAD_FAIL: "Exception has occurred while downloading.",
            ID_ERRTITLE_HTTP_CONNECT_INFO_FAIL: "Exception has occurred when checking connection.",
            ID_ERRTITLE_HTTP_CONNECT_DATA_FAIL: "Exception has occurred while transferring.",
            ID_ERRTITLE_NOT_PATH_UPLOAD_ERROR: "Save path does not exist in the server.",
            ID_ERRTITLE_SERVER_SCRIPT_ERROR: "Exception has occurred in the server script.",
            ID_ERRTITLE_SEND_INFO_FAIL: "Exception has occurred while checking the server.",
            ID_ERRTITLE_WRITE_UPLOAD_ERROR: "No permission to write in the upload path of the server ",
            ID_ERRTITLE_SEND_DATA_FAIL: "Exception has occurred while transferring data.",
            ID_ERRMSG_SEND_DATA_HTTP_FAIL: "Exception has occurred while transferring data.",
            ID_ERRMSG_CONNECT_FAILED: "Exception has occurred when connecting to server.",
            ID_ERRMSG_FILE_NOT_FOUND: "Save path does not exist.",
            ID_ERRMSG_SEND_HEADER_FAIL: "Exception has occurred while transferring header.",
            ID_ERRMSG_WRITE_PERMISSION: "No permission to save to this location.\n%s\nDo you want to save to default download folder?",
            ID_DLG_STR_DECRYPT_STATUS: "File is being decrypted.",
            ID_DLG_STR_COMPLETED_DOWNLOAD: "After transferring",
            ID_DLG_STR_CLOSE_WINDOW: "Close",
            ID_BTN_YES: "Yes",
            ID_BTN_NO: "No",
            ID_ERRMSG_CONTENT_LENGTH_ERROR: "Content-length error has occurred",
            ID_ERRMSG_IS_RUNNING_TRANSFER_WINDOW: "Files are being transferred. Please try again after transferring.",
            ID_ERRMSG_CERTIFICATE: "There’s a problem with this website’s security certificate."
        }
    };
})(innorix);