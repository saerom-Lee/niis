/*!
 * @file INNORIX Control Lang VI JS
 * @name langVietnamese
 */
(function(innorix) {
    "use strict";
    innorix.message = {
        error: {
            cannotFileExt: "Không thể đính kèm tập tin *.$arg1",
            fileDuplicate: "Tập tin này đã được đình kèm.",
            overFileCount: "Số lượng tập tin tối đa là $arg1.",
            overFileSize: "Một tập tin không quá $arg1.",
            overTotalSize: "Tổng kích thước các tập tin không quá $arg1.",
            zeroSizeFile: "",
            selectOneFile: "Please select a file to open.",
            trWinIsNotValid: "Transfer window URL does not exist."
        },
        dialog: {
            confirmAddUpResumeFiles: "Một tập tin bị gián đoạn. \n Bạn có muốn tiếp tục truyền? ",
            confirmUpResume: "Bạn có muốn tiếp tục tải lên?",
            alertChangeToMassControl: "Giao diện người dùng sẽ đổi sang chế độ tải lên nhiều tập tin \n bởi vì có hơn 100 tập tin đính kèm."
        },
        control: {
            andFiles: "và $arg1 tập tin ",
            chooseFile: "Chọn tập tin",
            fileDownload: "Tải về tập tin",
            unit: " tập tin",
            noFile: "Tập tin không tồn tại",
            addingFiles: "Các tập tin đính kèm",
            readyForTransfer: "Hoàn tất tối ưu hóa truyền nhiều tập tin",
            prepareForTransfer: "Chuẩn bị truyền tập tin",
            eta: "Estimated time"
        },
        controlHeader: {
            removeAll: "Remove all files",
            removeSelected: "Remove the selected file(s)",
            downAll: "Down all files",
            selected: "Tập tin được chọn",
            remove: "Loại bỏ",
            open: "Mở",
            down: "Tải về"
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
            addFile: "Đính kèm tập tin mới",
            addFolder: "ĐÍnh kèm thư mục mới",
            removeSelect: "Loại bỏ tập tin được chọn",
            removeAll: "Loại bỏ tất cả tập tin",
            executeFile: "Mở tập tin",
            upload: "Tải lên tất cả tập tin",
            download: "Tải về tất cả tập tin",
            downloadSelected: "Tải về các tập tin được chọn",
            openFile: "Mở tập tin",
            showAttrs: "Xem các thuộc tính"
        },
        controlExtraView: {
            item: "mục",
            items: "mục",
            attached: "đình kèm",
            name: "Tên",
            size: "Kích thước",
            type: "Kiểu",
            upload: "Upload",
            download: "Tải về",
            creationTime: "Ngày khởi tạo",
            modifiedTime: "Ngày sửa đổi",
            pageCount: "Đếm trang",
            author: "Tác giả",
            resolution: "Độ phân giải",
            selected: "chọn",
            fileAttached: " tập tin đính kèm",
            fileSelected: " tập tin được chọn",
            fileTotalSelInfo: "($arg2 of $arg1 file(s) selected)",
            estimatedTime: "Estimated time",
            total: "Tổng",
            hour: "giờ",
            min: "phút",
            sec: "sec",
            delFile: "Hủy bỏ"
        },
        tooltip: {
            unlimited: "unlimited",
            possibleCapacity: "Kích thước tập tin tối đa",
            fileAttached: " file(s) $arg1 attached",
            fileCount: "Số lượng tập tin",
            size: "Kích thước",
            unit: "",
            name: "Tên",
            type: "Kiểu",
            creationTime: "Ngày khởi tạo",
            modifiedTime: "Ngày sửa đổi",
            path: "Đường dẫn",
            imgSize: "Kích thước ảnh",
            fNumber: "Khẩu độ",
            iso: "Độ nhạy sáng ISO",
            exposureTime: "Thời gian phơi sáng",
            exposureBias: "Bù sáng",
            datePicTaken: "Ngày chụp",
            cameraModel: "Camera model",
            author: "Tác giả",
            pageCount: "Trang",
            version: "Phiên bản",
            revisionCount: "Sửa đổi",
            showImg: "Xem trước",
            showAttr: "Xem các thuộc tính"
        },
        plugin: {
            ID_ASK_FILENAME: "'%s' đã có trong thư mục.",
            ID_ASK_EXIST: "Tập tin đang tồn tại",
            ID_ASK_NEWFILESIZE: "Kích thước tập tin mới là %s.",
            ID_ASK_ALLCHECK: "Áp dụng tùy chọn này cho tất cả các tập tin",
            ID_ASK_BTN_OVERWRITE: "Ghi đè",
            ID_ASK_BTN_RESUME: "Tiếp tục",
            ID_ASK_BTN_SAVEAS: "Lưu mới",
            ID_ASK_BTN_CANCEL: "Hủy bỏ",
            ID_ASK_BTN_JUMP: "Bỏ qua",
            ID_ASK_BTN_SAVEAUTO: "Tự động đổi tên",
            ID_ASK_TEXT_AUTONAME_TAIL: "Tập tin sẽ được lưu tên %s",
            ID_ASK_ALLCHECK_NEW: "Apply this for the next %d conflicts",
            ID_ASK_SUN: "Chủ nhật",
            ID_ASK_MON: "Thứ Hai",
            ID_ASK_TUE: "Thứ Ba",
            ID_ASK_WED: "Thứ Tư",
            ID_ASK_THU: "Thứ Năm",
            ID_ASK_FRI: "Thứ Sáu",
            ID_ASK_SAT: "Thứ Bảy",
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
            ID_FD_ASK_BTN_OPEN: "Mở",
            ID_FD_ASK_BTN_SAVE: "Lưu mới",
            ID_FD_ASK_BTN_SAVEOPEN: "Mở sau khi lưu",
            ID_FD_ASK_MSG: "Bạn có muốn lưu và mở tập tin này?",
            ID_FD_ASK_TARGET: "Tập tin đíchㄴ",
            ID_FD_ASK_NAME: "Tên tập tin",
            ID_FD_ASK_TYPE: "Kiểu tập tin",
            ID_FD_ASK_ALLFILES: "Tất cả tập tin",
            ID_FD_ASK_SAVEAS: "Lưu mới",
            ID_ITEM_CANCEL: "Hủy bỏ",
            ID_ITEM_DONE: "Hoàn tất",
            ID_ITEM_DONEING: "Completing",
            ID_ITEM_PAUSE: "Pause",
            ID_ITEM_FAILED: "Error",
            ID_ITEM_TRANSFER_DOWNLOAD: "Downloading",
            ID_ITEM_TRANSFER: "Uploading",
            ID_MSG_LICENSE_INVALID: "Xin mời nhập license key.",
            ID_MSG_LICENCE_UNAUTH: "Tên miền hiện tại không phù hợp với license key.",
            ID_MSG_LICENCE_END: "Phiên bản dùng thử đã hết hạn.",
            ID_MSG_MODE_INVALID: "Chế độ lựa chọn không phù hợp với license key.",
            ID_MSG_LANGUAGE_SET_INVALID: "The selected language is not supported in this license key.",
            ID_MSG_PLATFORM_SET_INVALID: "The selected platform is not supported in this license key.",
            ID_MSG_ACCESS_DENIED: "Từ chối truy cập tập tin được chọn.",
            ID_MSG_ALREADY_EXISTS: "Tập tin này đã được đính kèm",
            ID_MSG_EXECUTE_FAIL: "Kiểu tập tin này không có chương trình mặc định",
            ID_MSG_FREE_SPACE_NOT_ENOUGH: "Không còn đủ chỗ trống trên đĩa",
            ID_MSG_TRANSFER_COMPLETE: "File transfer is completed.",
            ID_MSG_TRANSFER_WINDOW_ISNOT_VAILD: "Transfer window URL does not exist.",
            ID_STR_FILENAME: "Tên",
            ID_STR_FILESIZE: "Kiểu",
            ID_STR_STATUS: "Status",
            ID_STR_NERWORK_ERROR: "Exception has occurred when connecting to the network.",
            ID_STR_TRANSFER_RETRY: "Retry after %d sec.",
            ID_DLG_BTN_START: "Bắt đầu",
            ID_DLG_BTN_PAUSE: "Pause",
            ID_DLG_BTN_FOLDER_CHANGE: "Change path",
            ID_DLG_BTN_REMOVE_SELECTED: "Remove",
            ID_DLG_BTN_UP_ICON: "↑",
            ID_DLG_BTN_DOWN_ICON: "↓",
            ID_DLG_BTN_OK: "OK",
            ID_DLG_BTN_TRANSFER_CANCEL: "Cancel",
            ID_DLG_BTN_TRANSFER_RETRY: "Retry",
            ID_DLG_STR_ALERT: "Thông báo",
            ID_DLG_STR_TITLE_DS: "TẢI LÊN - INNODS",
            ID_DLG_STR_TITLE_AP: "TẢI LÊN - INNOAP",
            ID_DLG_STR_TITLE_FD: "TẢI VỀ - INNOFD",
            ID_DLG_STR_TITLE_EX: "TẢI LÊN - INNOEX",
            ID_DLG_STR_TITLE_EX1: "TẢI VỀ - INNOEX",
            ID_DLG_STR_TITLE_FD_PLUS: "TẢI VỀ - INNOFD Plus",
            ID_DLG_STR_TITLE_DS_PLUS: "TẢI LÊN - INNODS Plus",
            ID_DLG_STR_FD_INIT: 'Click the "Start" button to download.',
            ID_DLG_STR_DS_INIT: 'Click the "Start" button to upload.',
            ID_DLG_STR_CURRENT_FILES: "Current file :",
            ID_DLG_STR_TRANSFER_RATE: "Transfer rate :",
            ID_DLG_STR_PROGRESS: "Progress :",
            ID_DLG_STR_TIME_LEFT: "left",
            ID_DLG_STR_STATUS: "Status :",
            ID_DLG_STR_SAVE_PATH: "Đường dẫn lưu tập tin:",
            ID_DLG_STR_TIMER_SEC: "sec",
            ID_DLG_STR_TIMER_MIN: "phút",
            ID_DLG_STR_TIMER_HR: "",
            ID_DLG_STR_TIMER_HR1: "giờ",
            ID_DLG_STR_DOWN_AND_OPEN_FOLDER: "Open folder",
            ID_DLG_STR_WAIT: "Wait",
            ID_DLG_STR_FILE_SEND: "Đang truyền tập tin.",
            ID_DLG_STR_TRANSFER_PAUSE: "Đang tạm dừng truyền tập tin.",
            ID_DLG_STR_FILE_CANCEL: "Please wait. File transfer is being canceled.",
            ID_DLG_STR_FILE_WAIT: "Waiting for responses from the server.",
            ID_TRAY_STR_OPEN_DS: "Mở cửa sổ trạng thái tải lên.",
            ID_TRAY_STR_OPEN_FD: "Mở cửa sổ trạng thái tải về.",
            ID_TRAY_STR_MINIMIZE: "Thu nhỏ",
            ID_TRAY_STR_EXIT: "Thoát",
            ID_TRAY_STR_OPEN_LOGFOLDER: "Open the log folder.",
            ID_CLOSE_DLG_MSG_FD: "Are you sure you want to stop downloading?",
            ID_CLOSE_DLG_MSG_DS: "Are you sure you want to stop uploading?",
            ID_CLOSE_DLG_BTN_QUIT: "Thoát",
            ID_CLOSE_DLG_BTN_MINIMIZE: "Minimize to tray",
            ID_CLOSE_DLG_BTN_CANCEL: "Cancel",
            ID_ERRTITLE_GET_FILEINFO_FAIL: "Xảy ra ngoại lệ khi kiểm tra tập tin.",
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
            ID_ERRMSG_WRITE_PERMISSION: "Không được phép lưu vào đây.\n%s\nBạn có muốn lưu vào thư mục tải về mặc định?",
            ID_DLG_STR_DECRYPT_STATUS: "File is being decrypted.",
            ID_DLG_STR_COMPLETED_DOWNLOAD: "After transferring",
            ID_DLG_STR_CLOSE_WINDOW: "Close",
            ID_BTN_YES: "Có",
            ID_BTN_NO: "Không",
            ID_ERRMSG_CONTENT_LENGTH_ERROR: "Content-length error has occurred",
            ID_ERRMSG_IS_RUNNING_TRANSFER_WINDOW: "Đang truyền tập tin. Xin mời thử lại sau.",
            ID_ERRMSG_CERTIFICATE: "Chứng nhận bảo mật của website không hợp lệ"
        }
    };
})(innorix);