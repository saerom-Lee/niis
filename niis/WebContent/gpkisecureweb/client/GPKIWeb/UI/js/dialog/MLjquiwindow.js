/*
jQWidgets v4.1.1 (2016-Mar)
Copyright (c) 2011-2016 jQWidgets.
License: http://jqwidgets.com/license/
*/

(function(a) {
    a.MLjqui.MLjquiWidget("MLjquiWindow", "", {});
    a.extend(a.MLjqui._MLjquiWindow.prototype, {
        defineInstance: function() {
            var e = {
                height: "auto",
                width: 200,
                minHeight: 50,
                maxHeight: 600,
                minWidth: 50,
                maxWidth: 800,
                showCloseButton: true,
                disabled: false,
                autoOpen: true,
                keyboardCloseKey: "esc",
                title: "",
                content: "",
                draggable: true,
                resizable: true,
                animationType: "fade",
                closeAnimationDuration: 250,
                showAnimationDuration: 250,
                isModal: false,
                position: "center",
                closeButtonSize: 16,
                closeButtonAction: "hide",
                modalOpacity: 0.3,
                dragArea: null,
                okButton: null,
                cancelButton: null,
                dialogResult: {
                    OK: false,
                    Cancel: false,
                    None: true
                },
                collapsed: false,
                showCollapseButton: false,
                collapseAnimationDuration: 150,
                collapseButtonSize: 16,
                rtl: false,
                keyboardNavigation: true,
                headerHeight: null,
                _events: ["created", "closed", "moving", "moved", "open", "collapse", "expand", "open", "close", "resize"],
                initContent: null,
                enableResize: true,
                restricter: null,
                autoFocus: true,
                closing: null,
                _invalidArgumentExceptions: {
                    invalidHeight: "Invalid height!",
                    invalidWidth: "Invalid width!",
                    invalidMinHeight: "Invalid minHeight!",
                    invalidMaxHeight: "Invalid maxHeight!",
                    invalidMinWidth: "Invalid minWidth!",
                    invalidMaxWidth: "Invalid maxWidth",
                    invalidKeyCode: "Invalid keyCode!",
                    invalidAnimationType: "Invalid animationType!",
                    invalidCloseAnimationDuration: "Invalid closeAnimationDuration!",
                    invalidShowAnimationDuration: "Invalid showAnimationDuration!",
                    invalidPosition: "Invalid position!",
                    invalidCloseButtonSize: "Invalid closeButtonSize!",
                    invalidCollapseButtonSize: "Invalid collapseButtonSize!",
                    invalidCloseButtonAction: "Invalid cluseButtonAction!",
                    invalidModalOpacity: "Invalid modalOpacity!",
                    invalidDragArea: "Invalid dragArea!",
                    invalidDialogResult: "Invalid dialogResult!",
                    invalidIsModal: "You can have just one modal window!"
                },
                _enableResizeCollapseBackup: null,
                _enableResizeBackup: undefined,
                _heightBeforeCollapse: null,
                _minHeightBeforeCollapse: null,
                _mouseDown: false,
                _isDragging: false,
                _rightContentWrapper: null,
                _leftContentWrapper: null,
                _headerContentWrapper: null,
                _closeButton: null,
                _collapseButton: null,
                _title: null,
                _content: null,
                _mousePosition: {},
                _windowPosition: {},
                _modalBackground: null,
                _SCROLL_WIDTH: 21,
                _visible: true,
                modalBackgroundZIndex: 12990,
                modalZIndex: 18000,
                zIndex: 9001,
                _touchEvents: {
                    mousedown: a.MLjqui.mobile.getTouchEventName("touchstart"),
                    mouseup: a.MLjqui.mobile.getTouchEventName("touchend"),
                    mousemove: a.MLjqui.mobile.getTouchEventName("touchmove"),
                    mouseenter: "mouseenter",
                    mouseleave: "mouseleave",
                    click: a.MLjqui.mobile.getTouchEventName("touchstart")
                }
            };
            a.extend(true, this, e);
            return e
        },
        createInstance: function() {
            this.host.attr("role", "dialog");
            this.host.removeAttr("data-bind");
            this.host.detach();
            a(document.body).append(this.host);
            var f = this;
            var g = function() {
                var i = parseInt(a(f.restricter).css("padding-top"));
                var h = parseInt(a(f.restricter).css("padding-left"));
                var k = parseInt(a(f.restricter).css("padding-bottom"));
                var m = parseInt(a(f.restricter).css("padding-right"));
                var l = a(f.restricter).coord();
                f.dragArea = {
                    left: h + l.left,
                    top: i + l.top,
                    width: 1 + m + a(f.restricter).width(),
                    height: 1 + k + a(f.restricter).height()
                }
            };
            if (this.restricter) {
                g()
            }
            if (this.restricter) {
                this.addHandler(a(window), "resize." + this.element.id, function() {
                    g()
                });
                this.addHandler(a(window), "orientationchanged." + this.element.id, function() {
                    g()
                });
                this.addHandler(a(window), "orientationchange." + this.element.id, function() {
                    g()
                })
            }
            this._isTouchDevice = a.MLjqui.mobile.isTouchDevice();
            this._validateProperties();
            this._createStructure();
            this._refresh();
            if (!this.autoOpen) {
                this.host.css("display", "none")
            }
            if (a.MLjqui.browser.msie) {
                this.host.addClass(this.toThemeProperty("MLjqui-noshadow"))
            }
            if (!this.isModal) {
                this._fixWindowZIndex()
            }
            this._setStartupSettings();
            this._positionWindow();
            this._raiseEvent(0);
            if (this.autoOpen) {
                this._performLayout();
                var e = this;
                if (this.isModal) {
                    this._fixWindowZIndex("modal-show")
                }
                if (e.initContent) {
                    e.initContent();
                    e._contentInitialized = true
                }
                this._raiseEvent(7);
                this._raiseEvent(9)
            }
        },
        refresh: function() {
            this._performLayout()
        },
        _setStartupSettings: function() {
            if (this.disabled) {
                this.disable()
            }
            if (this.collapsed) {
                this.collapsed = false;
                this.collapse(0)
            }
            if (!this.autoOpen) {
                this.hide(null, 0.001, true);
                this._visible = false
            }
            if (this.title !== null && this.title !== "") {
                this.setTitle(this.title)
            }
            if (this.content !== null && this.content !== "") {
                this.setContent(this.content)
            }
            this.title = this._headerContentWrapper.html();
            this.content = this._content.html()
        },
        _fixWindowZIndex: function(n) {
            var f = a.data(document.body, "MLjquiwindows-list") || [],
                o = this.zIndex,
                i;
            if (!this.isModal) {
                if (this._indexOf(this.host, f) < 0) {
                    f.push(this.host)
                }
                a.data(document.body, "MLjquiwindows-list", f);
                if (f.length > 1) {
                    var g = f[f.length - 2];
                    if (g.css("z-index") == "auto") {
                        o = this.zIndex + f.length + 1
                    } else {
                        var e = this.zIndex;
                        o = parseInt(g.css("z-index"), 10) + 1;
                        if (o < e) {
                            o = e
                        }
                    }
                }
            } else {
                if (f) {
                    f = this._removeFromArray(this.host, f);
                    a.data(document.body, "MLjquiwindows-list", f)
                }
                var h = a.data(document.body, "MLjquiwindows-modallist");
                if (!h) {
                    if (n == "modal-show") {
                        var l = new Array();
                        l.push(this.host);
                        a.data(document.body, "MLjquiwindows-modallist", l);
                        h = l
                    } else {
                        a.data(document.body, "MLjquiwindows-modallist", new Array());
                        h = new Array()
                    }
                } else {
                    if (n == "modal-show") {
                        h.push(this.host)
                    } else {
                        var k = h.indexOf(this.host);
                        if (k != -1) {
                            h.splice(k, 1)
                        }
                    }
                }
                o = this.modalZIndex;
                var m = this;
                a.each(h, function(r) {
                    if (this.data()) {
                        if (this.data().MLjquiWindow) {
                            var q = this.data().MLjquiWindow.instance;
                            q._modalBackground.css("z-index", o);
                            q.host.css("z-index", o + 1);
                            o += 2
                        }
                    }
                });
                a.data(document.body, "MLjquiwindow-modal", this.host);
                return
            }
            this.host.css("z-index", o);
            this._sortByStyle("z-index", f)
        },
        _validateProperties: function() {
            try {
                this._validateSize();
                this._validateAnimationProperties();
                this._validateInteractionProperties();
                this._validateModalProperties();
                if (!this.position) {
                    throw new Error(this._invalidArgumentExceptions.invalidPosition)
                }
                if (isNaN(this.closeButtonSize) || parseInt(this.closeButtonSize) < 0) {
                    throw new Error(this._invalidArgumentExceptions.invalidCloseButtonSize)
                }
                if (isNaN(this.collapseButtonSize) || parseInt(this.collapseButtonSize) < 0) {
                    throw new Error(this._invalidArgumentExceptions.invalidCollapseButtonSize)
                }
            } catch (e) {
                alert(e);
            }
        },
        _validateModalProperties: function() {
            if (this.modalOpacity < 0 || this.modalOpacity > 1) {
                throw new Error(this._invalidArgumentExceptions.invalidModalOpacity)
            }
            if (this.isModal && !this._singleModalCheck()) {
                throw new Error(this._invalidArgumentExceptions.invalidIsModal)
            }
        },
        _validateSize: function() {
            this._validateSizeLimits();
            if (this.height !== "auto" && isNaN(parseInt(this.height))) {
                throw new Error(this._invalidArgumentExceptions.invalidHeight)
            }
            if (this.width !== "auto" && isNaN(parseInt(this.width))) {
                throw new Error(this._invalidArgumentExceptions.invalidWidth)
            }
            if (this.height !== "auto" && this.height < this.minHeight) {
                this.height = this.minHeight
            }
            if (this.width < this.minWidth) {
                this.width = this.minWidth
            }
            if (this.height !== "auto" && this.height > this.maxHeight) {
                this.height = this.maxHeight
            }
            if (this.width > this.maxWidth) {
                this.width = this.maxWidth
            }
            if (this.dragArea === null) {
                return
            }
            if (this.dragArea && ((this.dragArea.height !== null && this.host.height() > this.dragArea.height) || (parseInt(this.height, 10) > this.dragArea.height)) || (this.dragArea.width !== null && this.width > this.dragArea.width) || (this.maxHeight > this.dragArea.height || this.maxWidth > this.dragArea.width)) {}
        },
        _validateSizeLimits: function() {
            if (this.maxHeight == null) {
                this.maxHeight = 9999
            }
            if (this.minWidth == null) {
                this.minWidth = 0
            }
            if (this.maxWidth == null) {
                this.maxWidth = 9999
            }
            if (this.minHeight == null) {
                this.minHeight = 0
            }
            if (isNaN(parseInt(this.minHeight))) {
                throw new Error(this._invalidArgumentExceptions.invalidMinHeight)
            }
            if (isNaN(parseInt(this.maxHeight))) {
                throw new Error(this._invalidArgumentExceptions.invalidMaxHeight)
            }
            if (isNaN(parseInt(this.minWidth))) {
                throw new Error(this._invalidArgumentExceptions.invalidMinWidth)
            }
            if (isNaN(parseInt(this.maxWidth))) {
                throw new Error(this._invalidArgumentExceptions.invalidMaxWidth)
            }
            if (this.minHeight > this.maxHeight) {
                throw new Error(this._invalidArgumentExceptions.invalidMinHeight)
            }
            if (this.minWidth > this.maxWidth) {
                throw new Error(this._invalidArgumentExceptions.invalidMinWidth)
            }
        },
        _validateAnimationProperties: function() {
            if (this.animationType !== "fade" && this.animationType !== "slide" && this.animationType !== "combined" && this.animationType !== "none") {
                throw new Error(this._invalidArgumentExceptions.invalidAnimationType)
            }
            if (isNaN(parseInt(this.closeAnimationDuration), 10) || this.closeAnimationDuration < 0) {
                throw new Error(this._invalidArgumentExceptions.invalidCloseAnimationDuration)
            }
            if (isNaN(parseInt(this.showAnimationDuration), 10) || this.showAnimationDuration < 0) {
                throw new Error(this._invalidArgumentExceptions.invalidShowAnimationDuration)
            }
        },
        _validateInteractionProperties: function() {
            if (parseInt(this.keyCode, 10) < 0 || parseInt(this.keyCode, 10) > 130 && this.keyCode !== "esc") {
                throw new Error(this._invalidArgumentExceptions.invalidKeyCode)
            }
            if (this.dragArea !== null && (typeof this.dragArea.width === "undefined" || typeof this.dragArea.height === "undefined" || typeof this.dragArea.left === "undefined" || typeof this.dragArea.top === "undefined")) {
                throw new Error(this._invalidArgumentExceptions.invalidDragArea)
            }
            if (!this.dialogResult || (!this.dialogResult.OK && !this.dialogResult.Cancel && !this.dialogResult.None)) {
                throw new Error(this._invalidArgumentExceptions.invalidDialogResult)
            }
            if (this.closeButtonAction !== "hide" && this.closeButtonAction !== "close" && this.closeButtonAction !== "destroy") {
                throw new Error(this._invalidArgumentExceptions.invalidCloseButtonAction)
            }
        },
        _singleModalCheck: function() {
            var e = a.data(document.body, "MLjquiwindows-list") || [],
                f = e.length;
            while (f) {
                f -= 1;
                if (a(e[f].attr("id")).length > 0) {
                    if (a(e[f].attr("id")).MLjquiWindow("isModal")) {
                        return false
                    }
                }
            }
            return true
        },
        _createStructure: function() {
            var e = this.host.children("DIV");
            if (e.length === 1) {
                this._header = a("<div>" + this.host.attr("caption") + "</div>");
                this.host.prepend(this._header);
                this.host.attr("caption", "");
                this._content = a(e[0])
            } else {
                if (e.length === 2) {
                    this._header = a(e[0]);
                    this._content = a(e[1])
                } else {
                    throw new Error("Invalid structure!")
                }
            }
        },
        _refresh: function() {
            this._render();
            this._addStyles();
            this._performLayout();
            this._removeEventHandlers();
            this._addEventHandlers();
            this._initializeResize()
        },
        _render: function() {
            this._addHeaderWrapper();
            this._addCloseButton();
            this._addCollapseButton();
            this._removeModal();
            this._makeModal();
        },
        _addHeaderWrapper: function() {
            if (!this._headerContentWrapper) {
                this._header.wrapInner('<div style="float:left;"></div>');
                this._headerContentWrapper = this._header.children(0);
                if (this.headerHeight !== null) {
                    this._header.height(this.headerHeight)
                }
            }
        },
        _addCloseButton: function() {
            if (!this._closeButton) {
                this._closeButtonWrapper = a('<div class="' + this.toThemeProperty("MLjqui-window-close-button-background") + '"></div>');
                this._closeButton = a('<div style="width: 100%; height: 100%;" class="' + this.toThemeProperty("MLjqui-window-close-button") + " " + this.toThemeProperty("MLjqui-icon-close") + '"></div>');
                this._closeButtonWrapper.append(this._closeButton);
                this._header.append(this._closeButtonWrapper)
            }
        },
        _addCollapseButton: function() {
            if (!this._collapseButton) {
                this._collapseButtonWrapper = a('<div class="' + this.toThemeProperty("MLjqui-window-collapse-button-background") + '"></div>');
                this._collapseButton = a('<div style="width: 100%; height: 100%;" class="' + this.toThemeProperty("MLjqui-window-collapse-button") + " " + this.toThemeProperty("MLjqui-icon-arrow-up") + '"></div>');
                this._collapseButtonWrapper.append(this._collapseButton);
                this._header.append(this._collapseButtonWrapper)
            }
        },
        _removeModal: function() {
            if (!this.isModal && typeof this._modalBackground === "object" && this._modalBackground !== null && this._modalBackground.length >= 1) {
                a("." + this.toThemeProperty("MLjqui-window-modal")).remove();
                this._modalBackground = null
            }
        },
        focus: function() {
            try {
                this.host.focus();
                var f = this;
                setTimeout(function() {
                    f.host.focus()
                }, 10)
            } catch (e) {alert(e);}
        },
        _makeModal: function() {
            if (this.isModal && (!this._modalBackground || this._modalBackground.length < 1)) {
                var g = a.data(document.body, "MLjquiwindows-list");
                if (g) {
                    this._removeFromArray(this.host, g);
                    a.data(document.body, "MLjquiwindows-list", g)
                }
                this._modalBackground = a("<div></div>");
                this._modalBackground.addClass(this.toThemeProperty("MLjqui-window-modal"));
                this._setModalBackgroundStyles();
                a(document.body).append(this._modalBackground);
                this.addHandler(this._modalBackground, this._getEvent("click"), function() {
                    return false
                });
                var f = this;
                var e = function(i, k) {
                    var h = a(i).parents().get();
                    for (j = 0; j < h.length; j++) {
                        if (a(h[j]).is(k)) {
                            return true
                        }
                    }
                    return false
                };
                this.addHandler(this._modalBackground, "mouseup", function(h) {
                    f._stopResizing(f);
                    h.preventDefault()
                });
                this.addHandler(this._modalBackground, "mousedown", function(i) {
                    var h = f._getTabbables();
                    if (h.length > 0) {
                        h[0].focus(1);
                        setTimeout(function() {
                            h[0].focus(1)
                        }, 100)
                    }
                    i.preventDefault();
                    return false
                });
                this.addHandler(a(document), "keydown.window" + this.element.id, function(l) {
                    if (l.keyCode !== 9) {
                        return
                    }
                    var h = a.data(document.body, "MLjquiwindows-modallist");
                    if (h.length > 1) {
                        if (h[h.length - 1][0] != f.element) {
                            return
                        }
                    }
                    var k = f._getTabbables();
                    var m = null;
                    var i = null;
                    if (k.length > 0) {
                        m = k[0];
                        i = k[k.length - 1]
                    }
                    if (l.target == f.element) {
                        return
                    }
                    if (m == null) {
                        return
                    }
                    if (!e(l.target, f.host)) {
                        m.focus(1);
                        return false
                    }
                    if (l.target === i && !l.shiftKey) {
                        m.focus(1);
                        return false
                    } else {
                        if (l.target === m && l.shiftKey) {
                            i.focus(1);
                            return false
                        }
                    }
                })
            }
        },
        _addStyles: function() {
            this.host.addClass(this.toThemeProperty("MLjqui-rc-all"));
            this.host.addClass(this.toThemeProperty("MLjqui-window"));
            this.host.addClass(this.toThemeProperty("MLjqui-popup"));
            if (a.MLjqui.browser.msie) {
                this.host.addClass(this.toThemeProperty("MLjqui-noshadow"))
            }
            this.host.addClass(this.toThemeProperty("MLjqui-widget"));
            this.host.addClass(this.toThemeProperty("MLjqui-widget-content"));
            this._header.addClass(this.toThemeProperty("MLjqui-window-header"));
            this._content.addClass(this.toThemeProperty("MLjqui-window-content"));
            this._header.addClass(this.toThemeProperty("MLjqui-widget-header"));
            this._content.addClass(this.toThemeProperty("MLjqui-widget-content"));
            this._header.addClass(this.toThemeProperty("MLjqui-disableselect"));
            this._header.addClass(this.toThemeProperty("MLjqui-rc-t"));
            this._content.addClass(this.toThemeProperty("MLjqui-rc-b"));
            if (!this.host.attr("tabindex")) {
                this.element.tabIndex = 0;
                this.host.children().css("tab-index", 0)
            }
            this.host.attr("hideFocus", "true").css("outline", "none")
        },
        _performHeaderLayout: function() {
            this._handleHeaderButtons();
            this._header.css("position", "relative");
            if (this.rtl) {
                this._headerContentWrapper.css("direction", "rtl");
                this._headerContentWrapper.css("float", "right")
            } else {
                this._headerContentWrapper.css("direction", "ltr");
                this._headerContentWrapper.css("float", "left")
            }
            this._performHeaderCloseButtonLayout();
            this._performHeaderCollapseButtonLayout();
            this._centerElement(this._headerContentWrapper, this._header, "y", "margin");
            if (this.headerHeight) {
                this._centerElement(this._closeButtonWrapper, this._header, "y", "margin");
                this._centerElement(this._collapseButtonWrapper, this._header, "y", "margin")
            }
        },
        _handleHeaderButtons: function() {
            if (!this._closeButtonWrapper) {
                return
            }
            if (!this.showCloseButton) {
                this._closeButtonWrapper.css("visibility", "hidden")
            } else {
                this._closeButtonWrapper.css("visibility", "visible");
                this._closeButtonWrapper.width(this.closeButtonSize);
                this._closeButtonWrapper.height(this.closeButtonSize)
            }
            if (!this.showCollapseButton) {
                this._collapseButtonWrapper.css("visibility", "hidden")
            } else {
                this._collapseButtonWrapper.css("visibility", "visible");
                this._collapseButtonWrapper.width(this.collapseButtonSize);
                this._collapseButtonWrapper.height(this.collapseButtonSize)
            }
        },
        _performHeaderCloseButtonLayout: function() {
            if (!this._closeButtonWrapper) {
                return
            }
            var e = parseInt(this._header.css("padding-right"), 10);
            if (!isNaN(e)) {
                this._closeButtonWrapper.width(this._closeButton.width());
                if (!this.rtl) {
                    this._closeButtonWrapper.css("margin-right", e);
                    this._closeButtonWrapper.css("margin-left", "0px")
                } else {
                    this._closeButtonWrapper.css("margin-left", e);
                    this._closeButtonWrapper.css("margin-right", "0px")
                }
            }
            if (!this.rtl) {
                this._closeButtonWrapper.css({
                    position: "absolute",
                    right: "0px",
                    left: ""
                })
            } else {
                this._closeButtonWrapper.css({
                    position: "absolute",
                    left: "0px",
                    right: ""
                })
            }
        },
        _performHeaderCollapseButtonLayout: function() {
            if (!this._closeButtonWrapper) {
                return
            }
            var e = parseInt(this._header.css("padding-right"), 10);
            if (!isNaN(e)) {
                this._collapseButtonWrapper.width(this.collapseButtonSize);
                this._collapseButtonWrapper.height(this.collapseButtonSize);
                if (!this.rtl) {
                    this._collapseButtonWrapper.css("margin-right", e);
                    this._collapseButtonWrapper.css("margin-left", "0px")
                } else {
                    this._collapseButtonWrapper.css("margin-left", e);
                    this._collapseButtonWrapper.css("margin-right", "0px")
                }
            }
            if (!this.rtl) {
                this._collapseButtonWrapper.css({
                    position: "absolute",
                    right: (this.showCloseButton) ? this._closeButton.outerWidth(true) : 0,
                    left: ""
                })
            } else {
                this._collapseButtonWrapper.css({
                    position: "absolute",
                    left: (this.showCloseButton) ? this._closeButton.outerWidth(true) : 0,
                    right: ""
                })
            }
            this._centerElement(this._collapseButton, this._collapseButton.parent(), "y")
        },
        _performWidgetLayout: function() {
            var e;
            if (this.width !== "auto") {
                this.host.css("width", this.width)
            }
            if (!this.collapsed) {
                if (this.height !== "auto") {
                    this.host.height(this.height)
                } else {
                    this.host.height(this.host.height())
                }
                this.host.css("min-height", this.minHeight)
            }
            this._setChildrenLayout();
            e = this._validateMinSize();
            this.host.css({
                "max-height": this.maxHeight,
                "min-width": this.minWidth,
                "max-width": this.maxWidth
            });
            if (!e) {
                this._setChildrenLayout()
            }
        },
        _setChildrenLayout: function() {
            this._header.width(this.host.width() - (this._header.outerWidth(true) - this._header.width()));
            this._content.width(this.host.width() - (this._content.outerWidth(true) - this._content.width()));
            this._content.height(this.host.height() - this._header.outerHeight(true) - (this._content.outerHeight(true) - this._content.height()))
        },
        _validateMinSize: function() {
            var f = true;
            if (this.minHeight < this._header.height()) {
                this.minHeight = this._header.height();
                f = false
            }
            var h = this._header.children(0).outerWidth(true),
                e = this._header.children(1).outerWidth(true),
                g = h + e;
            if (this.minWidth < 100) {
                this.minWidth = Math.min(g, 100);
                f = false
            }
            return f
        },
        _centerElement: function(h, f, e, g) {
            if (typeof f.left === "number" && typeof f.top === "number" && typeof f.height === "number" && typeof f.width === "number") {
                this._centerElementInArea(h, f, e)
            } else {
                this._centerElementInParent(h, f, e, g)
            }
        },
        _centerElementInParent: function(e, m, h, f) {
            h = h.toLowerCase();
            if (f) {
                f += "-"
            } else {
                f = ""
            }
            if (h.indexOf("y") >= 0) {
                var g = e.outerHeight(true),
                    k = m.height(),
                    i = (Math.max(0, k - g)) / 2;
                e.css(f + "top", i + "px")
            }
            if (h.indexOf("x") >= 0) {
                var o = e.outerWidth(true);
                var n = m.width();
                var l = (Math.max(0, n - o)) / 2;
                e.css(f + "left", l + "px")
            }
        },
        _centerElementInArea: function(f, e, h) {
            h = h.toLowerCase();
            if (h.indexOf("y") >= 0) {
                var g = f.outerHeight(true);
                var k = e.height;
                var i = (k - g) / 2;
                f.css("top", i + e.top + "px")
            }
            if (h.indexOf("x") >= 0) {
                var n = f.outerWidth(true);
                var m = e.width;
                var l = (m - n) / 2;
                f.css("left", l + e.left + "px")
            }
        },
        _removeEventHandlers: function() {
            this.removeHandler(this._header, this._getEvent("mousedown"));
            this.removeHandler(this._header, this._getEvent("mousemove"));
            this.removeHandler(this._header, "focus");
            this.removeHandler(a(document), this._getEvent("mousemove") + "." + this.host.attr("id"));
            this.removeHandler(a(document), this._getEvent("mouseup") + "." + this.host.attr("id"));
            this.removeHandler(this.host, "keydown");
            this.removeHandler(this._closeButton, this._getEvent("click"));
            this.removeHandler(this._closeButton, this._getEvent("mouseenter"));
            this.removeHandler(this._closeButton, this._getEvent("mouseleave"));
            this.removeHandler(this._collapseButton, this._getEvent("click"));
            this.removeHandler(this._collapseButton, this._getEvent("mouseenter"));
            this.removeHandler(this._collapseButton, this._getEvent("mouseleave"));
            this.removeHandler(this.host, this._getEvent("mousedown"));
            this.removeHandler(a(this.okButton), this._getEvent("click"), this._setDialogResultHandler);
            this.removeHandler(a(this.cancelButton), this._getEvent("click"), this._setDialogResultHandler);
            this.removeHandler(this._header, this._getEvent("mouseenter"));
            this.removeHandler(this._header, this._getEvent("mouseleave"));
            this.removeHandler(this.host, "resizing", this._windowResizeHandler)
        },
        _removeFromArray: function(e, g) {
            var f = this._indexOf(e, g);
            if (f >= 0) {
                return g.splice(this._indexOf(e, g), 1)
            } else {
                return g
            }
        },
        _sortByStyle: function(e, l) {
            for (var h = 0; h < l.length; h++) {
                for (var f = l.length - 1; f > h; f--) {
                    var m = l[f],
                        k = l[f - 1],
                        g;
                    if (parseInt(m.css(e), 10) < parseInt(k.css(e), 10)) {
                        g = m;
                        l[f] = k;
                        l[f - 1] = g
                    }
                }
            }
        },
        _initializeResize: function() {
            if (this.resizable) {
                var e = this;
                this.initResize({
                    target: this.host,
                    alsoResize: e._content,
                    maxWidth: e.maxWidth,
                    minWidth: e.minWidth,
                    maxHeight: e.maxHeight,
                    minHeight: e.minHeight,
                    indicatorSize: 10,
                    resizeParent: e.dragArea
                })
            }
        },
        _removeResize: function() {
            this.removeResize()
        },
        _getEvent: function(e) {
            if (this._isTouchDevice) {
                return this._touchEvents[e]
            } else {
                return e
            }
        },
        _addEventHandlers: function() {
            this._addDragDropHandlers();
            this._addCloseHandlers();
            this._addCollapseHandlers();
            this._addFocusHandlers();
            this._documentResizeHandlers();
            this._closeButtonHover();
            this._collapseButtonHover();
            this._addDialogButtonsHandlers();
            this._addHeaderHoverEffect();
            this._addResizeHandlers();
            var e = this;
            this.addHandler(this._header, this._getEvent("mousemove"), function(f) {
                e._addHeaderCursorHandlers(e)
            })
        },
        _addResizeHandlers: function() {
            var e = this;
            this.addHandler(this.host, "resizing", this._windowResizeHandler, {
                self: this
            })
        },
        _windowResizeHandler: function(f) {
            var e = f.data.self;
            e._header.width(e.host.width() - (e._header.outerWidth(true) - e._header.width()));
            e.width = f.args.width;
            e.height = f.args.height
        },
        _addHeaderHoverEffect: function() {
            var e = this;
            this.addHandler(this._header, this._getEvent("mouseenter"), function() {
                a(this).addClass(e.toThemeProperty("MLjqui-window-header-hover"))
            });
            this.addHandler(this._header, this._getEvent("mouseleave"), function() {
                a(this).removeClass(e.toThemeProperty("MLjqui-window-header-hover"))
            })
        },
        _addDialogButtonsHandlers: function() {
            if (this.okButton) {
                this.addHandler(a(this.okButton), this._getEvent("click"), this._setDialogResultHandler, {
                    self: this,
                    result: "ok"
                })
            }
            if (this.cancelButton) {
                this.addHandler(a(this.cancelButton), this._getEvent("click"), this._setDialogResultHandler, {
                    self: this,
                    result: "cancel"
                })
            }
        },
        _documentResizeHandlers: function() {
            var e = this;
            if (this.isModal) {
                this.addHandler(a(window), "resize.window" + this.element.id, function() {
                    if (typeof e._modalBackground === "object" && e._modalBackground !== null) {
                        if (e.isOpen()) {
                            e._modalBackground.hide()
                        }
                        if (!e.restricter) {
                            e._modalBackground.width(e._getDocumentSize().width);
                            e._modalBackground.height(e._getDocumentSize().height)
                        } else {
                            e._modalBackground.css("left", e.dragArea.left);
                            e._modalBackground.css("top", e.dragArea.top);
                            e._modalBackground.width(e.dragArea.width);
                            e._modalBackground.height(e.dragArea.height)
                        }
                        if (e.isOpen()) {
                            e._modalBackground.show()
                        }
                    }
                })
            }
        },
        _setDialogResultHandler: function(f) {
            var e = f.data.self;
            e._setDialogResult(f.data.result);
            e.closeWindow();
        },
        _setDialogResult: function(e) {
            this.dialogResult.OK = false;
            this.dialogResult.None = false;
            this.dialogResult.Cancel = false;
            e = e.toLowerCase();
            switch (e) {
                case "ok":
                    this.dialogResult.OK = true;
                    break;
                case "cancel":
                    this.dialogResult.Cancel = true;
                    break;
                default:
                    this.dialogResult.None = true
            }
        },
        _getDocumentSize: function() {
            var e = a.MLjqui.browser.msie && a.MLjqui.browser.version < 9;
            var f = e ? 4 : 0;
            var g = f;
            if (document.body.scrollHeight > document.body.clientHeight && e) {
                f = this._SCROLL_WIDTH
            }
            if (document.body.scrollWidth > document.body.clientWidth && e) {
                g = this._SCROLL_WIDTH
            }
            return {
                width: a(document).width() - f,
                height: a(document).height() - g
            }
        },
        _closeButtonHover: function() {
            var e = this;
            this.addHandler(this._closeButton, this._getEvent("mouseenter"), function() {
                e._closeButton.addClass(e.toThemeProperty("MLjqui-window-close-button-hover"))
            });
            this.addHandler(this._closeButton, this._getEvent("mouseleave"), function() {
                e._closeButton.removeClass(e.toThemeProperty("MLjqui-window-close-button-hover"))
            })
        },
        _collapseButtonHover: function() {
            var e = this;
            this.addHandler(this._collapseButton, this._getEvent("mouseenter"), function() {
                e._collapseButton.addClass(e.toThemeProperty("MLjqui-window-collapse-button-hover"))
            });
            this.addHandler(this._collapseButton, this._getEvent("mouseleave"), function() {
                e._collapseButton.removeClass(e.toThemeProperty("MLjqui-window-collapse-button-hover"))
            })
        },
        _setModalBackgroundStyles: function() {
            if (this.isModal) {
                this._modalBackground.fadeTo(0, this.modalOpacity);
                this._modalBackground.css({
                    position: "absolute",
                    top: "0px",
                    left: "0px",
                    width: this._getDocumentSize().width,
                    height: this._getDocumentSize().height,
                    "z-index": this.modalBackgroundZIndex
                });
                if (!this.autoOpen) {
                    this._modalBackground.css("display", "none")
                }
            }
        },
        _addFocusHandlers: function() {
            var e = this;
            this.addHandler(this.host, this._getEvent("mousedown"), function() {
                if (!e.isModal) {
                    e.bringToFront()
                }
            })
        },
        _indexOf: function(f, g) {
            for (var e = 0; e < g.length; e++) {
                if (g[e][0] === f[0]) {
                    return e
                }
            }
            return -1
        },
        _addCloseHandlers: function() {
            var e = this;
            this.addHandler(this._closeButton, this._getEvent("click"), function(f) {
                return e._closeWindow(f)
            });
            if (this.keyboardCloseKey !== "none") {
                if (typeof this.keyboardCloseKey !== "number" && this.keyboardCloseKey.toLowerCase() === "esc") {
                    this.keyboardCloseKey = 27
                }
            }
            this.addHandler(this.host, "keydown", function(f) {
                if (f.keyCode === e.keyboardCloseKey && e.keyboardCloseKey != null && e.keyboardCloseKey != "none") {
                    e._closeWindow(f)
                } else {
                    e._handleKeys(f)
                }
            }, {
                self: this
            });
            this.addHandler(this.host, "keyup", function(f) {
                if (!e.keyboardNavigation) {
                    return
                }
                if (e._moved) {
                    var i = e.host.coord();
                    var h = i.left;
                    var g = i.top;
                    e._raiseEvent(3, h, g, h, g);
                    e._moved = false
                }
            })
        },
        _handleKeys: function(f) {
            if (!this.keyboardNavigation) {
                return
            }
            if (!this._headerFocused) {
                return
            }
            if (a(document.activeElement).ischildof(this._content)) {
                return
            }
            var e = f.ctrlKey;
            var n = f.keyCode;
            var l = this.host.coord();
            var k = l.left;
            var m = l.top;
            var g = this._getDraggingArea();
            var h = this.host.width();
            var o = this.host.height();
            var p = true;
            var i = 10;
            switch (n) {
                case 37:
                    if (!e) {
                        if (this.draggable) {
                            if (k - i >= 0) {
                                this.move(k - i, m)
                            }
                        }
                    } else {
                        if (this.resizable) {
                            this.resize(h - i, o)
                        }
                    }
                    p = false;
                    break;
                case 38:
                    if (!e) {
                        if (this.draggable) {
                            if (m - i >= 0) {
                                this.move(k, m - i)
                            }
                        }
                    } else {
                        if (this.resizable) {
                            this.resize(h, o - i)
                        }
                    }
                    p = false;
                    break;
                case 39:
                    if (!e) {
                        if (this.draggable) {
                            if (k + h + i <= g.width) {
                                this.move(k + i, m)
                            }
                        }
                    } else {
                        if (this.resizable) {
                            this.resize(h + i, o)
                        }
                    }
                    p = false;
                    break;
                case 40:
                    if (!e) {
                        if (this.draggable) {
                            if (m + o + i <= g.height) {
                                this.move(k, m + i)
                            }
                        }
                    } else {
                        if (this.resizable) {
                            this.resize(h, o + i)
                        }
                    }
                    p = false;
                    break
            }
            if (!p) {
                if (f.preventDefault) {
                    f.preventDefault()
                }
                if (f.stopPropagation) {
                    f.stopPropagation()
                }
            }
            return p
        },
        _addCollapseHandlers: function() {
            var e = this;
            this.addHandler(this._collapseButton, this._getEvent("click"), function() {
                if (!e.collapsed) {
                    e.collapse()
                } else {
                    e.expand()
                }
            })
        },
        _closeWindow: function(e) {
            this.closeWindow();
            return false
        },
        _addHeaderCursorHandlers: function(e) {
            if (e.resizeArea && e.resizable && !e.collapsed) {
                e._header.css("cursor", e._resizeWrapper.css("cursor"));
                return
            } else {
                if (e.draggable) {
                    e._header.css("cursor", "move");
                    return
                }
            }
            e._header.css("cursor", "default");
            if (e._resizeWrapper && e._resizeWrapper.length > 0) {
                e._resizeWrapper.css("cursor", "default")
            }
        },
        _addDragDropHandlers: function() {
            if (this.draggable) {
                var e = this;
                this.addHandler(this.host, "focus", function() {
                    e._headerFocused = true
                });
                this.addHandler(this.host, "blur", function() {
                    e._headerFocused = false
                });
                this.addHandler(this._header, "focus", function() {
                    e._headerFocused = true;
                    return false
                });
                this.addHandler(this._header, this._getEvent("mousedown"), function(k, i, l) {
                    if (i) {
                        k.pageX = i
                    }
                    if (l) {
                        k.pageY = l
                    }
                    e._headerMouseDownHandler(e, k);
                    return true
                });
                this.addHandler(this._header, "dragstart", function(i) {
                    if (i.preventDefault) {
                        i.preventDefault()
                    }
                    return false
                });
                this.addHandler(this._header, this._getEvent("mousemove"), function(i) {
                    return e._headerMouseMoveHandler(e, i)
                });
                this.addHandler(a(document), this._getEvent("mousemove") + "." + this.host.attr("id"), function(i) {
                    return e._dragHandler(e, i)
                });
                this.addHandler(a(document), this._getEvent("mouseup") + "." + this.host.attr("id"), function(i) {
                    return e._dropHandler(e, i)
                });
                try {
                    if (document.referrer != "" || window.frameElement) {
                        var h = null;
                        if (window.top != null && window.top != window.self) {
                            if (window.parent && document.referrer) {
                                h = document.referrer
                            }
                        }
                        if (h && h.indexOf(document.location.host) != -1) {
                            var g = function(i) {
                                e._dropHandler(e, i)
                            };
                            if (window.top.document.addEventListener) {
                                window.top.document.addEventListener("mouseup", g, false)
                            } else {
                                if (window.top.document.attachEvent) {
                                    window.top.document.attachEvent("onmouseup", g)
                                }
                            }
                        }
                    }
                } catch (f) {alert(f);}
            }
        },
        _headerMouseDownHandler: function(f, g) {
            if (!f.isModal) {
                f.bringToFront()
            }
            if (f._resizeDirection == null) {
                var h = a.MLjqui.mobile.getTouches(g);
                var i = h[0];
                var e = a.MLjqui.position(g);
                f._mousePosition.x = e.left;
                f._mousePosition.y = e.top;
                f._mouseDown = true;
                f._isDragging = false
            }
        },
        _headerMouseMoveHandler: function(f, i) {
            if (f._mouseDown && !f._isDragging) {
                var k = a.MLjqui.mobile.getTouches(i);
                var l = k[0];
                var h = l.pageX,
                    g = l.pageY;
                var e = a.MLjqui.position(i);
                h = e.left;
                g = e.top;
                if ((h + 3 < f._mousePosition.x || h - 3 > f._mousePosition.x) || (g + 3 < f._mousePosition.y || g - 3 > f._mousePosition.y)) {
                    f._isDragging = true;
                    f._mousePosition = {
                        x: h,
                        y: g
                    };
                    f._windowPosition = {
                        x: f.host.coord().left,
                        y: f.host.coord().top
                    };
                    a(document.body).addClass(f.toThemeProperty("MLjqui-disableselect"))
                }
                if (f._isTouchDevice) {
                    i.preventDefault();
                    return true
                }
                return false
            }
            if (f._isDragging) {
                if (f._isTouchDevice) {
                    i.preventDefault();
                    return true
                }
                return false
            }
            return true
        },
        _dropHandler: function(g, k) {
            var f = true;
            if (g._isDragging && !g.isResizing && !g._resizeDirection) {
                var e = parseInt(g.host.css("left"), 10),
                    l = parseInt(g.host.css("top"), 10),
                    i = (g._isTouchDevice) ? 0 : k.pageX,
                    h = (g._isTouchDevice) ? 0 : k.pageY;
                g.enableResize = g._enableResizeBackup;
                g._enableResizeBackup = "undefined";
                g._raiseEvent(3, e, l, i, h);
                f = false;
                if (k.preventDefault != "undefined") {
                    k.preventDefault()
                }
                if (k.originalEvent != null) {
                    k.originalEvent.mouseHandled = true
                }
                if (k.stopPropagation != "undefined") {
                    k.stopPropagation()
                }
            }
            g._isDragging = false;
            g._mouseDown = false;
            a(document.body).removeClass(g.toThemeProperty("MLjqui-disableselect"));
            return f
        },
        _dragHandler: function(p, h) {
            if (p._isDragging && !p.isResizing && !p._resizeDirection) {
                var o = (p._isTouchDevice) ? h.originalEvent.which : h.which;
                if (typeof p._enableResizeBackup === "undefined") {
                    p._enableResizeBackup = p.enableResize
                }
                p.enableResize = false;
                if (o === 0 && a.MLjqui.browser.msie && a.MLjqui.browser.version < 8) {
                    return p._dropHandler(p, h)
                }
                var m = a.MLjqui.mobile.getTouches(h);
                var l = m[0];
                var n = a.MLjqui.position(h);
                var k = n.left,
                    i = n.top,
                    g = k - p._mousePosition.x,
                    f = i - p._mousePosition.y,
                    e = p._windowPosition.x + g,
                    q = p._windowPosition.y + f;
                p.move(e, q, h);
                h.preventDefault();
                return false
            }
            return true
        },
        _validateCoordinates: function(e, l, i, k) {
            var h = this._getDraggingArea();
            e = (e < h.left) ? h.left : e;
            l = (l < h.top) ? h.top : l;
            var f = this.host.outerWidth(true);
            var g = this.host.outerHeight(true);
            if (e + f >= h.width + h.left - 2 * k) {
                e = h.width + h.left - f - k
            }
            if (l + g >= h.height + h.top - i) {
                l = h.height + h.top - g - i
            }
            return {
                x: e,
                y: l
            }
        },
        _performLayout: function() {
            this._performHeaderLayout();
            this._performWidgetLayout()
        },
        _parseDragAreaAttributes: function() {
            if (this.dragArea !== null) {
                this.dragArea.height = parseInt(this.dragArea.height, 10);
                this.dragArea.width = parseInt(this.dragArea.width, 10);
                this.dragArea.top = parseInt(this.dragArea.top, 10);
                this.dragArea.left = parseInt(this.dragArea.left, 10)
            }
        },
        _positionWindow: function() {
            this._parseDragAreaAttributes();
            if (this.position instanceof Array && this.position.length === 2 && typeof this.position[0] === "number" && typeof this.position[1] === "number") {
                this.host.css({
                    left: this.position[0],
                    top: this.position[1]
                })
            } else {
                if (this.position instanceof Object) {
                    if (this.position.left) {
                        this.host.offset(this.position)
                    } else {
                        if (this.position.x !== undefined && this.position.y != undefined) {
                            this.host.css({
                                left: this.position.x,
                                top: this.position.y
                            })
                        } else {
                            if (this.position.center) {
                                this._centerElement(this.host, this.position.center, "xy");
                                var g = this.position.center.coord();
                                var f = parseInt(this.host.css("left"));
                                var e = parseInt(this.host.css("top"));
                                this.host.css({
                                    left: f + g.left,
                                    top: e + g.top
                                })
                            }
                        }
                    }
                } else {
                    this._positionFromLiteral()
                }
            }
        },
        _getDraggingArea: function() {
            var e = {};
            e.left = ((this.dragArea && this.dragArea.left) ? this.dragArea.left : 0);
            e.top = ((this.dragArea && this.dragArea.top) ? this.dragArea.top : 0);
            e.width = ((this.dragArea && this.dragArea.width) ? this.dragArea.width : this._getDocumentSize().width);
            e.height = ((this.dragArea && this.dragArea.height) ? this.dragArea.height : this._getDocumentSize().height);
            return e
        },
        _positionFromLiteral: function() {
            if (!(this.position instanceof Array)) {
                this.position = this.position.split(",")
            }
            var e = this.position.length,
                f = this._getDraggingArea();
            while (e) {
                e -= 1;
                this.position[e] = this.position[e].replace(/ /g, "");
                switch (this.position[e]) {
                    case "top":
                        this.host.css("top", f.top);
                        break;
                    case "left":
                        this.host.css("left", f.left);
                        break;
                    case "bottom":
                        this.host.css("top", f.height - this.host.height() + f.top);
                        break;
                    case "right":
                        this.host.css("left", f.left + f.width - this.host.width());
                        break;
                    default:
                        if (!this.dragArea) {
                            f = a(window)
                        }
                        this._centerElement(this.host, f, "xy");
                        break
                }
            }
        },
        _raiseEvent: function(g) {
            var f = this._events[g],
                h = a.Event(f),
                e = {};
            if (g === 2 || g === 3) {
                e.x = arguments[1];
                e.y = arguments[2];
                e.pageX = arguments[3];
                e.pageY = arguments[4]
            }
            if (f === "closed" || f === "close") {
                e.dialogResult = this.dialogResult
            }
            h.args = e;
            return this.host.trigger(h)
        },
        destroy: function() {
            this.removeHandler(a(window), "resize.window" + this.element.id);
            this._removeEventHandlers();
            this._destroy()
        },
        _destroy: function() {
            if (this.restricter) {
                this.removeHandler(a(window), "resize." + this.element.id);
                this.removeHandler(a(window), "orientationchanged." + this.element.id);
                this.removeHandler(a(window), "orientationchange." + this.element.id)
            }
            this.host.remove();
            if (this._modalBackground !== null) {
                this._modalBackground.remove()
            }
        },
        _toClose: function(f, e) {
            return ((f && e[0] === this.element) || (e[0] !== this.element && typeof e[0] === "object"))
        },
        propertyChangedHandler: function(e, f, h, g) {
            this._validateProperties();
            switch (f) {
                case "rtl":
                    this._performLayout();
                    break;
                case "dragArea":
                    this._positionWindow();
                    break;
                case "collapseButtonSize":
                    this._performLayout();
                    break;
                case "closeButtonSize":
                    this._performLayout();
                    break;
                case "isModal":
                    this._refresh();
                    this._fixWindowZIndex();
                    break;
                case "keyboardCloseKey":
                    this._removeEventHandlers();
                    this._addEventHandlers();
                    break;
                case "disabled":
                    if (g) {
                        this.disable()
                    } else {
                        this.disabled = true;
                        this.enable()
                    }
                    break;
                case "showCloseButton":
                case "showCollapseButton":
                    this._performLayout();
                    break;
                case "height":
                    this._performLayout();
                    break;
                case "width":
                    this._performLayout();
                    break;
                case "title":
                    this.setTitle(g);
                    this.title = g;
                    break;
                case "content":
                    this.setContent(g);
                    break;
                case "draggable":
                    this._removeEventHandlers();
                    this._addEventHandlers();
                    this._removeResize();
                    this._initializeResize();
                    break;
                case "resizable":
                    this.enableResize = g;
                    if (g) {
                        this._initializeResize()
                    } else {
                        this._removeResize()
                    }
                    break;
                case "position":
                    this._positionWindow();
                    break;
                case "modalOpacity":
                    this._setModalBackgroundStyles();
                    break;
                case "okButton":
                    if (g) {
                        this._addDialogButtonsHandlers()
                    } else {
                        this.removeHandler(this.okButton)
                    }
                    break;
                case "cancelButton":
                    if (g) {
                        this._addDialogButtonsHandlers()
                    } else {
                        this.removeHandler(this.cancelButton)
                    }
                    break;
                case "collapsed":
                    if (g) {
                        if (!h) {
                            this.collapsed = false;
                            this.collapse(0)
                        }
                    } else {
                        if (h) {
                            this.collapsed = true;
                            this.expand(0)
                        }
                    }
                case "theme":
                    a.MLjqui.utilities.setTheme(h, g, this.host);
                    break;
                case "enableResize":
                    return;
                case "maxWidth":
                case "maxHeight":
                case "minWidth":
                case "minHeight":
                    e._performLayout();
                    e._removeResize();
                    e._initializeResize();
                    return;
                default:
                    return
            }
        },
        collapse: function(g) {
            if (!this.collapsed && !this.host.is(":animated")) {
                if (this.host.css("display") == "none") {
                    return
                }
                var e = this,
                    h = this._header.outerHeight(true),
                    i = parseInt(this._header.css("border-bottom-width"), 10),
                    f = parseInt(this._header.css("margin-bottom"), 10),
                    g = !isNaN(parseInt(g)) ? g : this.collapseAnimationDuration;
                if (!isNaN(i)) {
                    h -= 2 * i
                }
                if (!isNaN(f)) {
                    h += f
                }
                this._heightBeforeCollapse = this.host.height();
                this._minHeightBeforeCollapse = this.host.css("min-height");
                this.host.css("min-height", h);
                this.host.animate({
                    height: h
                }, g, function() {
                    e.collapsed = true;
                    e._collapseButton.addClass(e.toThemeProperty("MLjqui-window-collapse-button-collapsed"));
                    e._collapseButton.addClass(e.toThemeProperty("MLjqui-icon-arrow-down"));
                    e._content.css("display", "none");
                    e._raiseEvent(5);
                    e._raiseEvent(9);
                    a.MLjqui.aria(e, "aria-expanded", false)
                })
            }
        },
        expand: function(f) {
            if (this.collapsed && !this.host.is(":animated")) {
                var e = this,
                    f = !isNaN(parseInt(f)) ? f : this.collapseAnimationDuration;
                this.host.animate({
                    height: this._heightBeforeCollapse
                }, f, function() {
                    e.collapsed = false;
                    e.host.css("min-height", e._minHeightBeforeCollapse);
                    e._collapseButton.removeClass(e.toThemeProperty("MLjqui-window-collapse-button-collapsed"));
                    e._collapseButton.removeClass(e.toThemeProperty("MLjqui-icon-arrow-down"));
                    e._content.css("display", "block");
                    e._raiseEvent(6);
                    e._performWidgetLayout();
                    e._raiseEvent(9);
                    a.MLjqui.aria(e, "aria-expanded", true)
                })
            }
        },
        closeAll: function(h) {
            var h = true;
            var g = a.data(document.body, "MLjquiwindows-list"),
                f = g.length,
                e = a.data(document.body, "MLjquiwindow-modal") || [];
            while (f) {
                f -= 1;
                if (this._toClose(h, g[f])) {
                    g[f].MLjquiWindow("closeWindow", "close");
                    g.splice(f, 1)
                }
            }
            if (this._toClose(h, e)) {
                e.MLjquiWindow("closeWindow", "close");
                a.data(document.body, "MLjquiwindow-modal", [])
            }
            a.data(document.body, "MLjquiwindows-list", g)
        },
        setTitle: function(e) {
            a.MLjqui.utilities.html(this._headerContentWrapper, e);
            this.title = e;
            this._performLayout()
        },
        setContent: function(f) {
            this._contentInitialized = false;
            var e = this._content,
                g = false;
            while (!g) {
                e.css("height", "auto");
                e.css("width", "auto");
                if (e.is(".MLjqui-window")) {
                    g = true
                } else {
                    e = e.parent()
                }
            }
            a.MLjqui.utilities.html(this._content, f);
            this._performLayout()
        },
        disable: function() {
				console.log("dddddddddddddddddddddddddddddddddddddd");
            this.disabled = true;
            this._removeEventHandlers();
            this._header.addClass(this.toThemeProperty("MLjqui-window-header-disabled"));
            this._closeButton.addClass(this.toThemeProperty("MLjqui-window-close-button-disabled"));
            this._collapseButton.addClass(this.toThemeProperty("MLjqui-window-collapse-button-disabled"));
            this._content.addClass(this.toThemeProperty("MLjqui-window-content-disabled"));
            this.host.addClass(this.toThemeProperty("MLjqui-window-disabled"));
            this.host.addClass(this.toThemeProperty("MLjqui-fill-state-disabled"));
            this._removeResize()
        },
        enable: function() {
            if (this.disabled) {
                this._addEventHandlers();
                this._header.removeClass(this.toThemeProperty("MLjqui-window-header-disabled"));
                this._content.removeClass(this.toThemeProperty("MLjqui-window-content-disabled"));
                this._closeButton.removeClass(this.toThemeProperty("MLjqui-window-close-button-disabled"));
                this._collapseButton.removeClass(this.toThemeProperty("MLjqui-window-collapse-button-disabled"));
                this.host.removeClass(this.toThemeProperty("MLjqui-window-disabled"));
                this.host.removeClass(this.toThemeProperty("MLjqui-fill-state-disabled"));
                this.disabled = false;
                this._initializeResize()
            }
        },
        isOpen: function() {
            return this._visible
        },
        closeWindow: function(f) {
            var e = this;
            f = (typeof f === "undefined") ? this.closeButtonAction : f;
            this.hide(function() {
                if (f === "close") {
                    e._destroy();
                }
            })
        },
        bringToFront: function() {
            var f = a.data(document.body, "MLjquiwindows-list");
            if (this.isModal) {
                f = a.data(document.body, "MLjquiwindows-modallist");
                this._fixWindowZIndex("modal-hide");
                this._fixWindowZIndex("modal-show");
                return
            }
            var l = f[f.length - 1],
                k = parseInt(l.css("z-index"), 10),
                g = this._indexOf(this.host, f);
            for (var e = f.length - 1; e > g; e -= 1) {
                var h = parseInt(f[e].css("z-index"), 10) - 1;
                f[e].css("z-index", h)
            }
            this.host.css("z-index", k);
            this._sortByStyle("z-index", f)
        },
        hide: function(h, g, e) {
            if (this.closing) {
                var f = this.closing();
                if (f == false) {
                    return
                }
            }
            g = g || this.closeAnimationDuration;
            switch (this.animationType) {
                case "none":
                    this.host.css("display", "none");
                    break;
                case "fade":
                    this.host.fadeOut(g, function() {
                        if (h instanceof Function) {
                            h()
                        }
                    });
                case "slide":
                    this.host.slideUp(g, function() {
                        if (h instanceof Function) {
                            h()
                        }
                    });
                case "combined":
                    this.host.hide(g, function() {
                        if (h instanceof Function) {
                            h()
                        }
                    })
            }
            this._visible = false;
            if (this.isModal) {
                this._modalBackground.hide();
                this._fixWindowZIndex("modal-hide")
            }
            if (e !== true) {
                this._raiseEvent(1);
                this._raiseEvent(8)
            }
        },
        open: function(f, e) {
            this.show(f, e)
        },
        close: function(g, f, e) {
            this.hide(g, f, e)
        },
        show: function(h, g) {
            this._setDialogResult("none");
            g = g || this.showAnimationDuration;
            switch (this.animationType) {
                case "none":
                    this.host.css("display", "block");
                    break;
                case "fade":
                    this.host.fadeIn(g, function() {
                        if (h instanceof Function) {
                            h()
                        }
                    });
                    break;
                case "slide":
                    this.host.slideDown(g, function() {
                        if (h instanceof Function) {
                            h()
                        }
                    });
                    break;
                case "combined":
                    this.host.show(g, function() {
                        if (h instanceof Function) {
                            h()
                        }
                    });
                    break
            }
            if (this.isModal) {
                this._modalBackground.show();
                this._fixWindowZIndex("modal-show")
            }
            var f = this;
            if (!this._visible) {
                if (g > 150 && this.animationType != "none") {
                    setTimeout(function() {
                        if (!f._contentInitialized) {
                            if (f.initContent) {
                                f.initContent();
                                f._contentInitialized = true
                            }
                        }
                        f._raiseEvent(7);
                        f._raiseEvent(9)
                    }, g - 150)
                } else {
                    if (!f._contentInitialized) {
                        if (f.initContent) {
                            f.initContent();
                            f._contentInitialized = true
                        }
                    }
                    this._raiseEvent(7);
                    f._raiseEvent(9)
                }
            }
            this._visible = true;
            this._performLayout();
            if (this.autoFocus) {
                var e = function() {
                    if (!f._isTouchDevice) {
                        f._content.focus()
                    }
                };
                e();
                setTimeout(function() {
                    e()
                }, 100)
            }
        },
        _getTabbables: function() {
            var f = this._content.find("*");
            var e = new Array();
            a.each(f, function() {
                if (d(this)) {
                    e[e.length] = this
                }
            });
            return e
        },
        move: function(q, p, e, h) {
            var g = 0,
                f = 0,
                n, k, i, q = parseInt(q, 10),
                p = parseInt(p, 10);
            if (a.MLjqui.browser.msie) {
                if (a(window).width() > a(document).width() && !this.dragArea) {
                    f = this._SCROLL_WIDTH
                }
                if (a(window).height() < a(document).height() && document.documentElement.clientWidth > document.documentElement.scrollWidth && !this.dragArea) {
                    g = this._SCROLL_WIDTH
                }
            }
            n = this._validateCoordinates(q, p, f, g);
            if (parseInt(this.host.css("left"), 10) !== n.x || parseInt(this.host.css("top"), 10) !== n.y) {
                if (e) {
                    var m = a.MLjqui.mobile.getTouches(e);
                    var l = m[0];
                    var o = a.MLjqui.position(e);
                    k = o.left;
                    i = o.top
                }
                if (k == undefined) {
                    k = q
                }
                if (i == undefined) {
                    i = p
                }
                if (h !== false) {
                    this._raiseEvent(2, n.x, n.y, k, i)
                }
            }
            this.element.style.left = n.x + "px";
            this.element.style.top = n.y + "px";
            this._moved = true
        }
    });

    function c(g, e) {
        var k = g.nodeName.toLowerCase();
        if ("area" === k) {
            var i = g.parentNode,
                h = i.name,
                f;
            if (!g.href || !h || i.nodeName.toLowerCase() !== "map") {
                return false
            }
            f = a("img[usemap=#" + h + "]")[0];
            return !!f && b(f)
        }
        return (/input|select|textarea|button|object/.test(k) ? !g.disabled : "a" == k ? g.href || e : e) && b(g)
    }

    function b(e) {
        return !a(e).parents().andSelf().filter(function() {
            return a.css(this, "visibility") === "hidden" || a.expr.filters.hidden(this)
        }).length
    }

    function d(g) {
        var e = a.attr(g, "tabindex"),
            f = isNaN(e);
        return (f || e >= 0) && c(g, !f)
    }
}(MLjquiBaseFramework));
(function(b) {
    var a = (function(c) {
        return {
            resizeConfig: function() {
                this.resizeTarget = null;
                this.resizeIndicatorSize = 5;
                this.resizeTargetChildren = null;
                this.isResizing = false;
                this.resizeArea = false;
                this.minWidth = 1;
                this.maxWidth = 100;
                this.minHeight = 1;
                this.maxHeight = 100;
                this.resizeParent = null;
                this.enableResize = true;
                this._cursorBackup;
                this._resizeEvents = ["resizing", "resized", "resize"];
                this._resizeMouseDown = false;
                this._resizeCurrentMode = null;
                this._mouseResizePosition = {};
                this._resizeMethods = null;
                this._SCROLL_WIDTH = 21
            },
            _resizeExceptions: {
                invalidTarget: "Invalid target!",
                invalidMinHeight: "Invalid minimal height!",
                invalidMaxHeight: "Invalid maximum height!",
                invalidMinWidth: "Invalid minimum width!",
                invalidMaxWidth: "Invalid maximum width!",
                invalidIndicatorSize: "Invalid indicator size!",
                invalidSize: "Invalid size!"
            },
            removeResize: function() {
                if (this.resizeTarget) {
                    var f = c(this.resizeTarget.children(".MLjqui-resize"));
                    f.detach();
                    var e = f.children();
                    this._removeResizeEventListeners();
                    for (var d = 0; d < e.length; d += 1) {
                        c(e[d]).detach();
                        this.resizeTarget.append(e[d])
                    }
                    f.remove()
                }
            },
            initResize: function(d) {
                this.resizeConfig();
                this.resizeTarget = c(d.target);
                this.resizeIndicatorSize = d.indicatorSize || 10;
                this.maxWidth = d.maxWidth || 100;
                this.minWidth = d.minWidth || 1;
                this.maxHeight = d.maxHeight || 100;
                this.minHeight = d.minHeight || 1;
                this.resizeParent = d.resizeParent;
                this._parseResizeParentProperties();
                this._validateResizeProperties();
                this._validateResizeTargetDimensions();
                this._getChildren(this.resizeTarget.maxWidth, this.resizeTarget.minWidth, this.resizeTarget.maxHeight, this.resizeTarget.minHeight, d.alsoResize);
                this._refreshResize();
                this._cursorBackup = this.resizeTarget.css("cursor");
                if (this._cursorBackup === "auto") {
                    this._cursorBackup = "default"
                }
            },
            _validateResizeTargetDimensions: function() {
                this.resizeTarget.maxWidth = this.maxWidth;
                this.resizeTarget.minWidth = ((3 * this.resizeIndicatorSize > this.minWidth) ? 3 * this.resizeIndicatorSize : this.minWidth);
                this.resizeTarget.maxHeight = this.maxHeight;
                this.resizeTarget.minHeight = ((3 * this.resizeIndicatorSize > this.minHeight) ? 3 * this.resizeIndicatorSize : this.minHeight)
            },
            _parseResizeParentProperties: function() {
                if (this.resizeParent) {
                    this.resizeParent.left = parseInt(this.resizeParent.left, 10);
                    this.resizeParent.top = parseInt(this.resizeParent.top, 10);
                    this.resizeParent.width = parseInt(this.resizeParent.width, 10);
                    this.resizeParent.height = parseInt(this.resizeParent.height, 10)
                }
            },
            _getChildren: function(h, e, g, i, d) {
                this.resizeTargetChildren = c(d);
                this.resizeTargetChildren.toArray();
                var f = this.resizeTargetChildren.length;
                while (f) {
                    f -= 1;
                    this.resizeTargetChildren[f] = c(this.resizeTargetChildren[f])
                }
            },
            _refreshResize: function() {
                this._renderResize();
                this._performResizeLayout();
                this._removeResizeEventListeners();
                this._addResizeEventHandlers()
            },
            _renderResize: function() {
                this.resizeTarget.wrapInner(c("<div></div>"));
                this._resizeWrapper = this.resizeTarget.children(0);
                this._resizeWrapper.addClass("MLjqui-resize");
                this._resizeWrapper.addClass("MLjqui-rc-all");
                this._resizeWrapper.css("z-index", 8000)
            },
            _performResizeLayout: function() {
                this._resizeWrapper.height(this.resizeTarget.height());
                this._resizeWrapper.width(this.resizeTarget.width())
            },
            _removeResizeEventListeners: function() {
                var d = this.resizeTarget.attr("id");
                this.removeHandler(this._resizeWrapper, "mousemove.resize" + d);
                this.removeHandler(this._resizeWrapper, "mousedown.resize" + d);
                this.removeHandler(c(document), "mousemove.resize" + d);
                this.removeHandler(c(document), "mouseup.resize" + d)
            },
            _addResizeEventHandlers: function() {
                var g = this.resizeTarget.attr("id");
                var d = this;
                this.addHandler(this._resizeWrapper, "mousemove.resize." + g, function(h) {
                    d._resizeCursorChangeHandler(d, h)
                });
                this.addHandler(this._resizeWrapper, "mousedown.resize." + g, function(h) {
                    d._resizeMouseDownHandler(d, h)
                });
                this.addHandler(c(document), "mousemove.resize." + g, function(h) {
                    return d._resizeHandler(d, h)
                });
                this.addHandler(c(document), "mouseup.resize." + g, function(h) {
                    d._stopResizing(d, h)
                });
                try {
                    if (document.referrer != "" || window.frameElement) {
                        var f = function(h) {
                            d._stopResizing(d, h)
                        };
                        if (window.top.document.addEventListener) {
                            window.top.document.addEventListener("mouseup", f, false)
                        } else {
                            if (window.top.document.attachEvent) {
                                window.top.document.attachEvent("onmouseup", f)
                            }
                        }
                    }
                } catch (e) {alert(e);}
            },
            _stopResizing: function(d, e) {
                if (d.enableResize) {
                    if (d.isResizing) {
                        d._raiseResizeEvent(1)
                    }
                    d._resizeMouseDown = false;
                    d.isResizing = false;
                    d._resizeDirection = null;
                    if (d.resizeTarget) {
                        d.resizeTarget.removeClass("MLjqui-disableselect")
                    }
                }
                if (d._cursorBackup == "undefined") {
                    d._cursorBackup = "default"
                }
                if (d._resizeWrapper) {
                    d._resizeWrapper.css("cursor", d._cursorBackup)
                }
            },
            _resizeHandler: function(d, e) {
                if (d.enableResize && !d.collapsed) {
                    if (d.isResizing && d._resizeDirection) {
                        if (e.which === 0 && c.MLjqui.browser.msie && c.MLjqui.browser.version < 9) {
                            d._stopResizing(e)
                        }
                        d._performResize(e.pageX, e.pageY);
                        return false
                    } else {
                        return d._resizeCaptureCursor(e.pageX, e.pageY)
                    }
                }
            },
            _resizeCaptureCursor: function(e, d) {
                if (this._resizeMouseDown && !this.isResizing && this._resizeDirection) {
                    if ((e + 3 < this._mouseResizePosition.x || e - 3 > this._mouseResizePosition.x) || (d + 3 < this._mouseResizePosition.y || d - 3 > this._mouseResizePosition.y)) {
                        this._changeCursor(e - parseInt(this.resizeTarget.css("left")), d - parseInt(this.resizeTarget.css("top")));
                        this._mouseResizePosition = {
                            x: e,
                            y: d
                        };
                        this._prepareResizeMethods(this._resizeDirection);
                        this._resizeBackupData();
                        this.isResizing = true;
                        this.resizeTarget.addClass("MLjqui-disableselect");
                        return false
                    }
                }
            },
            _resizeBackupData: function() {
                this.resizeTarget.lastWidth = this.resizeTarget.width();
                this.resizeTarget.lastHeight = this.resizeTarget.height();
                this.resizeTarget.x = parseInt(this.resizeTarget.css("left"), 10);
                this.resizeTarget.y = parseInt(this.resizeTarget.css("top"), 10);
                this._resizeBackupChildrenSize()
            },
            _resizeBackupChildrenSize: function() {
                var d = this.resizeTargetChildren.length,
                    e;
                while (d) {
                    d -= 1;
                    e = this.resizeTargetChildren[d];
                    this.resizeTargetChildren[d].lastWidth = e.width();
                    this.resizeTargetChildren[d].lastHeight = e.height()
                }
            },
            _performResize: function(g, f) {
                var e = g - this._mouseResizePosition.x,
                    d = f - this._mouseResizePosition.y;
                if (this._resizeDirection) {
                    this._resize(this.resizeTarget, e, d)
                }
            },
            _resizeCursorChangeHandler: function(d, e) {
                if (d.enableResize && !d.collapsed) {
                    if (!d.isResizing) {
                        d._changeCursor(e.pageX - parseInt(d.resizeTarget.css("left")), e.pageY - parseInt(d.resizeTarget.css("top")))
                    }
                }
            },
            _resizeMouseDownHandler: function(d, e) {
                if (d.enableResize) {
                    if (d._resizeDirection !== null) {
                        d._resizeMouseDown = true;
                        d._mouseResizePosition.x = e.pageX;
                        d._mouseResizePosition.y = e.pageY;
                        e.preventDefault()
                    }
                }
            },
            _validateResizeProperties: function() {
                try {
                    if (!this.resizeTarget || this.resizeTarget.length !== 1) {
                        throw new Error(this._resizeExceptions.invalidTarget)
                    }
                    if (this.minHeight < 0 || isNaN(parseInt(this.minHeight))) {
                        throw new Error(this._resizeExceptions.invalidMinHeight)
                    }
                    if (this.maxHeight <= 0 || isNaN(parseInt(this.maxHeight))) {
                        throw new Error(this._resizeExceptions.invalidMaxHeight)
                    }
                    if (this.minWidth < 0 || isNaN(parseInt(this.minWidth))) {
                        throw new Error(this._resizeExceptions.invalidMinWidth)
                    }
                    if (this.maxWidth < 0 || isNaN(parseInt(this.maxWidth))) {
                        throw new Error(this._resizeExceptions.invalidMaxWidth)
                    }
                    if (this.resizeIndicatorSize < 0 || isNaN(parseInt(this.resizeIndicatorSize))) {
                        throw new Error(this._resizeExceptions.invalidIndicatorSize)
                    }
                    if (this.minHeight > this.maxHeight || this.minWidth > this.maxWidth) {
                        throw new Error(this._resizeExceptions.invalidSize)
                    }
                } catch (d) {
                    alert(d);
                }
            },
            _changeCursor: function(d, e) {
                if (this.isResizing || this._resizeMouseDown) {
                    return
                }
                this.resizeArea = true;
                if (d <= this.resizeIndicatorSize && d >= 0 && e <= this.resizeIndicatorSize && e > 0) {
                    this._resizeWrapper.css("cursor", "nw-resize");
                    this._resizeDirection = "topleft"
                } else {
                    if (e <= this.resizeIndicatorSize && e > 0 && d >= this.resizeTarget.width() - this.resizeIndicatorSize) {
                        this._resizeWrapper.css("cursor", "ne-resize");
                        this._resizeDirection = "topright"
                    } else {
                        if (e >= this.resizeTarget.height() - this.resizeIndicatorSize && e < this.resizeTarget.height() && d <= this.resizeIndicatorSize && d >= 0) {
                            this._resizeWrapper.css("cursor", "sw-resize");
                            this._resizeDirection = "bottomleft"
                        } else {
                            if (e >= this.resizeTarget.height() - this.resizeIndicatorSize && e < this.resizeTarget.height() && d >= this.resizeTarget.width() - this.resizeIndicatorSize && d < this.resizeTarget.width()) {
                                this._resizeWrapper.css("cursor", "se-resize");
                                this._resizeDirection = "bottomright"
                            } else {
                                if (d <= this.resizeIndicatorSize && d >= 0) {
                                    this._resizeWrapper.css("cursor", "e-resize");
                                    this._resizeDirection = "left"
                                } else {
                                    if (e <= this.resizeIndicatorSize && e > 0) {
                                        this._resizeWrapper.css("cursor", "n-resize");
                                        this._resizeDirection = "top"
                                    } else {
                                        if (e >= this.resizeTarget.height() - this.resizeIndicatorSize && e < this.resizeTarget.height()) {
                                            this._resizeWrapper.css("cursor", "n-resize");
                                            this._resizeDirection = "bottom"
                                        } else {
                                            if (d >= this.resizeTarget.width() - this.resizeIndicatorSize && d < this.resizeTarget.width()) {
                                                this._resizeWrapper.css("cursor", "e-resize");
                                                this._resizeDirection = "right"
                                            } else {
                                                this._resizeWrapper.css("cursor", this._cursorBackup);
                                                this._resizeDirection = null;
                                                this.resizeArea = false
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            _prepareResizeMethods: function(d) {
                this._resizeMethods = [];
                if (d.indexOf("left") >= 0) {
                    this._resizeMethods.push(this._resizeLeft)
                }
                if (d.indexOf("top") >= 0) {
                    this._resizeMethods.push(this._resizeTop)
                }
                if (d.indexOf("right") >= 0) {
                    this._resizeMethods.push(this._resizeRight)
                }
                if (d.indexOf("bottom") >= 0) {
                    this._resizeMethods.push(this._resizeBottom)
                }
            },
            _validateResize: function(g, d, h, f, e) {
                if (h === "horizontal" || h === "both") {
                    return this._validateWidth(g, f, e)
                } else {
                    if (h === "vertical" || h === "both") {
                        return this._validateHeight(d, f, e)
                    }
                }
                return {
                    result: false,
                    fix: 0
                }
            },
            _getParent: function() {
                if (this.resizeParent !== null && this.resizeParent !== "undefined" && this.resizeParent.height && this.resizeParent.width && this.resizeParent.top && this.resizeParent.left) {
                    return this.resizeParent
                }
                return {
                    left: 0,
                    top: 0,
                    width: c(document).width(),
                    height: c(document).height()
                }
            },
            _validateHeight: function(f, k, i) {
                var l = 0,
                    e = 2,
                    d = false,
                    h = f,
                    g = this._getParent();
                if (c(window).width() > c(document).width() && c.MLjqui.browser.msie && g.height === c(document).height()) {
                    l = this._SCROLL_WIDTH
                }
                if (i === "bottom" && (f + k.position().top + l + e > g.height + g.top)) {
                    return {
                        fix: g.height - k.position().top - l - e + g.top,
                        result: false
                    }
                }
                if (i === "top" && k.lastHeight - f + k.y < g.top) {
                    return {
                        fix: f + (k.lastHeight - f + k.y) - g.top,
                        result: false
                    }
                }
                if (f < k.minHeight) {
                    return {
                        fix: k.minHeight,
                        result: false
                    }
                }
                if (f > k.maxHeight) {
                    return {
                        fix: k.maxHeight,
                        result: false
                    }
                }
                return {
                    result: true,
                    fix: f
                }
            },
            _validateWidth: function(k, i, h) {
                var l = 0,
                    e = 2,
                    d = false,
                    g = k,
                    f = this._getParent();
                if (c(window).height() < c(document).height() && c.MLjqui.browser.msie && document.documentElement.clientWidth >= document.documentElement.scrollWidth && f.width === c(document).width()) {
                    l = this._SCROLL_WIDTH
                }
                if (h === "right" && (k + i.position().left + l + e > f.width + f.left)) {
                    return {
                        fix: f.width - i.position().left - l - e + f.left,
                        result: false
                    }
                }
                if (h === "left" && (i.lastWidth - k + i.x < f.left)) {
                    return {
                        fix: k + (i.lastWidth - k + i.x) - f.left,
                        result: false
                    }
                }
                if (k < i.minWidth) {
                    return {
                        fix: i.minWidth,
                        result: false
                    }
                }
                if (k > i.maxWidth) {
                    return {
                        fix: i.maxWidth,
                        result: false
                    }
                }
                return {
                    result: true,
                    fix: k
                }
            },
            _resize: function(h, e, d) {
                var l = this._resizeDirection;
                var k = this._resizeMethods.length;
                for (var g = 0; g < k; g++) {
                    if (this._resizeMethods[g] instanceof Function) {
                        var f = {
                            element: h,
                            x: e,
                            y: d,
                            self: this
                        };
                        this._resizeMethods[g](f)
                    }
                }
                this._performResizeLayout()
            },
            resize: function(g, d) {
                if (this.resizable) {
                    var f = g - this.host.width();
                    var e = d - this.host.height();
                    var h = "right";
                    if (e != 0) {
                        h = "bottom"
                    }
                    this._resizeDirection = h;
                    this._prepareResizeMethods(this._resizeDirection);
                    this._resizeBackupData();
                    this.isResizing = true;
                    this._resize(this.resizeTarget, f, e);
                    this.isResizing = false
                }
            },
            _setResizeChildrenSize: function(e, f) {
                var h = this.resizeTargetChildren.length;
                while (h) {
                    h--;
                    if (f === "width") {
                        var g = this.resizeTargetChildren[h].lastWidth - (this.resizeTarget.lastWidth - e);
                        if (g < this.resizeTarget.maxWidth && g > 0) {
                            this.resizeTargetChildren[h].width(g)
                        }
                    } else {
                        var d = this.resizeTargetChildren[h].lastHeight - (this.resizeTarget.lastHeight - e);
                        if (d < this.resizeTarget.maxHeight && d > 0) {
                            this.resizeTargetChildren[h].height(d)
                        }
                    }
                }
            },
            _resizeRight: function(e) {
                var f = e.element.lastWidth + e.x,
                    d = e.self._validateResize(f, 0, "horizontal", e.element, "right");
                if (!d.result) {
                    f = d.fix
                }
                if (e.element.width() !== f) {
                    e.self._setResizeChildrenSize(f, "width");
                    e.element.width(f);
                    e.self._raiseResizeEvent(0)
                }
                return f
            },
            _resizeLeft: function(f) {
                var g = f.element.lastWidth - f.x,
                    e = f.self._validateResize(g, 0, "horizontal", f.element, "left"),
                    d = f.element.x + f.x;
                if (!e.result) {
                    d = f.element.x + (f.element.lastWidth - e.fix);
                    g = e.fix;
                    return
                }
                if (f.element.width() !== g) {
                    f.self._setResizeChildrenSize(g, "width");
                    f.element.width(g);
                    f.element.css("left", d);
                    f.self._raiseResizeEvent(0)
                }
                return g
            },
            _resizeBottom: function(f) {
                var e = f.element.lastHeight + f.y,
                    d = f.self._validateResize(0, e, "vertical", f.element, "bottom");
                if (!d.result) {
                    e = d.fix
                }
                if (f.element.height() !== e) {
                    f.self._setResizeChildrenSize(e, "height");
                    f.element.height(e);
                    f.self._raiseResizeEvent(0)
                }
                return e
            },
            _resizeTop: function(f) {
                var e = f.element.lastHeight - f.y,
                    d = f.self._validateResize(0, e, "vertical", f.element, "top"),
                    g = f.element.y + f.y;
                if (!d.result) {
                    g = f.element.y + (f.element.lastHeight - d.fix);
                    e = d.fix;
                    return
                }
                if (f.element.height() !== e) {
                    f.self._setResizeChildrenSize(e, "height");
                    f.element.height(e);
                    f.element.css("top", g);
                    f.self._raiseResizeEvent(0)
                }
                return e
            },
            _raiseResizeEvent: function(f) {
                var e = this._resizeEvents[f],
                    g = c.Event(e),
                    d = {};
                d.width = parseInt(this.resizeTarget[0].style.width);
                d.height = parseInt(this.resizeTarget[0].style.height);
                g.args = d;
                if (f == 0) {
                    var e = this._resizeEvents[2],
                        h = c.Event(e);
                    h.args = d;
                    this.resizeTarget.trigger(h)
                }
                return this.resizeTarget.trigger(g)
            }
        }
    }(MLjquiBaseFramework));
    b.extend(b.MLjqui._MLjquiWindow.prototype, a)
}(MLjquiBaseFramework));