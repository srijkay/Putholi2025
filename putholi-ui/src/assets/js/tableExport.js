(function ($) {
    $.fn.extend({
        tableExport: function (options) {
            var defaults = {
                separator: ',', ignoreColumn: [], tableName: 'yourTableName', type: 'csv', pdfFontSize: 14, pdfLeftMargin: 20, escape: 'true',
                htmlContent: 'false', consoleLog: 'false', fileName: 'yourFileName', sheetName: 'yourDataName', footer: 'true'
            };

            var options = $.extend(defaults, options);
            var el = this;
            var nameOfFile = "download";
            if (defaults.type == 'csv' || defaults.type == 'txt') {
                var tdData = "";
                $(el).find('thead').find('tr').each(function () {
                    tdData += "\n";
                    $(this).filter(':visible').find('th').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                tdData += '"' + parseString($(this)) + '"' + defaults.separator;
                                var num = $(this).attr('colspan');
                                tdData = getColSpanForCSV(num, tdData);
                            }
                        }
                    });
                    tdData = $.trim(tdData);
                    tdData = $.trim(tdData).substring(0, tdData.length - 1)
                });
                $(el).find('tbody').find('tr').each(function () {
                    tdData += "\n";
                    $(this).filter(':visible').find('td').each(function (index, data) {
                        if ($(this).css('display') != 'none' && $(this).find('ul').length <= 0) {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                tdData += '"';
                                if ($(this).children().length > 0) {
                                    $(this).filter(':visible').find('*').each(function (index, data) {
                                        if ($(this).css('display') != 'none' && $(this).children().length < 1) {
                                            tdData += parseString($(this))
                                            return false;
                                        }
                                    });
                                }
                                else {
                                    tdData += parseString($(this))
                                }
                                tdData += '"' + defaults.separator;
                            }
                        }
                    });
                    tdData = $.trim(tdData).substring(0, tdData.length - 1)
                });

                if (defaults.footer == 'true') {

                    //two empty rows between summary and details
                    tdData += "\n";
                    tdData += "\n";
                    $(el).find('tfoot').find('tr').each(function () {
                        tdData += "\n";
                        $(this).filter(':visible').find('td').each(function (index, data) {
                            if ($(this).css('display') != 'none' && $(this).find('ul').length <= 0) {
                                if (defaults.ignoreColumn.indexOf(index) == -1) {
                                    tdData += '"';
                                    if ($(this).children().length > 0) {
                                        $(this).filter(':visible').find('*').each(function (index, data) {
                                            if ($(this).css('display') != 'none' && $(this).children().length < 1) {
                                                tdData += parseString($(this))
                                                return false;
                                            }
                                        });
                                    }
                                    else {
                                        tdData += parseString($(this))
                                    }
                                    tdData += '"' + defaults.separator;
                                }
                            }
                        });
                        tdData = $.trim(tdData).substring(0, tdData.length - 1)
                    });
                }


                if (defaults.consoleLog == 'true') { console.log(tdData) }
                var base64data = "base64," + $.base64.encode(tdData);
                var uri = 'data:application/' + defaults.type + ';' + base64data;
                var downloadLink = document.createElement("a");
                downloadLink.href = uri;
                if (defaults.fileName != 'yourFileName') {
                    downloadLink.download = defaults.fileName + "." + defaults.type;
                }
                else {
                    downloadLink.download = nameOfFile + "." + defaults.type;
                }
                document.body.appendChild(downloadLink);
                downloadLink.click();
                document.body.removeChild(downloadLink)
            } else if (defaults.type == 'sql') {
                var tdData = "INSERT INTO `" + defaults.tableName + "` (";
                $(el).find('thead').find('tr').each(function () {
                    $(this).filter(':visible').find('th').each(function (index, data) { if ($(this).css('display') != 'none') { if (defaults.ignoreColumn.indexOf(index) == -1) { tdData += '`' + parseString($(this)) + '`,' } } });
                    tdData = $.trim(tdData);
                    tdData = $.trim(tdData).substring(0, tdData.length - 1)
                });
                tdData += ") VALUES ";
                $(el).find('tbody').find('tr').each(function () {
                    tdData += "(";
                    $(this).filter(':visible').find('td').each(function (index, data) { if ($(this).css('display') != 'none' && $(this).find('ul').length <= 0) { if (defaults.ignoreColumn.indexOf(index) == -1) { tdData += '"' + parseString($(this)) + '",' } } });
                    tdData = $.trim(tdData).substring(0, tdData.length - 1);
                    tdData += "),"
                });
                tdData = $.trim(tdData).substring(0, tdData.length - 1);
                tdData += ";";
                if (defaults.consoleLog == 'true') { console.log(tdData) }
                var base64data = "base64," + $.base64.encode(tdData);
                window.open('data:application/sql;filename=exportData;' + base64data)
            } else if (defaults.type == 'json') {
                var jsonHeaderArray = [];
                $(el).find('thead').find('tr').each(function () {
                    var tdData = ""; var jsonArrayTd = [];
                    $(this).filter(':visible').find('th').each(function (index, data) { if ($(this).css('display') != 'none') { if (defaults.ignoreColumn.indexOf(index) == -1) { jsonArrayTd.push(parseString($(this))) } } });
                    jsonHeaderArray.push(jsonArrayTd)
                });
                var jsonArray = [];
                $(el).find('tbody').find('tr').each(function () {
                    var tdData = ""; var jsonArrayTd = [];
                    $(this).filter(':visible').find('td').each(function (index, data) { if ($(this).css('display') != 'none' && $(this).find('ul').length <= 0) { if (defaults.ignoreColumn.indexOf(index) == -1) { jsonArrayTd.push(parseString($(this))) } } });
                    jsonArray.push(jsonArrayTd)
                });
                var jsonExportArray = [];
                jsonExportArray.push({ header: jsonHeaderArray, data: jsonArray });
                if (defaults.consoleLog == 'true') { console.log(JSON.stringify(jsonExportArray)) }
                var base64data = "base64," + $.base64.encode(JSON.stringify(jsonExportArray));
                window.open('data:application/json;filename=exportData;' + base64data)
            } else if (defaults.type == 'xml') {
                var xml = '<?xml version="1.0" encoding="utf-8"?>';
                xml += '<tabledata><fields>';
                $(el).find('thead').find('tr').each(function () { $(this).filter(':visible').find('th').each(function (index, data) { if ($(this).css('display') != 'none') { if (defaults.ignoreColumn.indexOf(index) == -1) { xml += "<field>" + parseString($(this)) + "</field>" } } }) });
                xml += '</fields><data>';
                var rowCount = 1;
                $(el).find('tbody').find('tr').each(function () {
                    xml += '<row id="' + rowCount + '">';
                    var colCount = 0;
                    $(this).filter(':visible').find('td').each(function (index, data) {
                        if ($(this).css('display') != 'none' && $(this).find('ul').length <= 0) { if (defaults.ignoreColumn.indexOf(index) == -1) { xml += "<column-" + colCount + ">" + parseString($(this)) + "</column-" + colCount + ">" } }
                        colCount++
                    });
                    rowCount++;
                    xml += '</row>'
                });
                xml += '</data></tabledata>'
                if (defaults.consoleLog == 'true') { console.log(xml) }
                var base64data = "base64," + $.base64.encode(xml);
                window.open('data:application/xml;filename=exportData;' + base64data)
            } else if (defaults.type == 'excel' || defaults.type == 'doc' || defaults.type == 'powerpoint') {
                var excel = "<table>";
                $(el).find('thead').find('tr').each(function () {
                    excel += "<tr>";
                    $(this).filter(':visible').find('th').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                colSpan = ($(this).prop('colSpan') > 0) ? $(this).prop('colSpan') : 1;
                                rowSpan = ($(this).prop('rowSpan') > 0) ? $(this).prop('rowSpan') : 1;
                                excel += "<td colSpan='" + colSpan + "' rowSpan='" + rowSpan + "'>" + parseString($(this)) + "</td>";
                            }
                        }
                    });
                    excel += '</tr>'
                });
                var rowCount = 1;
                $(el).find('tbody').find('tr').each(function () {
                    if ($(this).css('display') != 'none') {
                        excel += "<tr>";
                        var colCount = 0;
                        $(this).filter(':visible').find('td').each(function (index, data) {
                            if ($(this).css('display') != 'none' && $(this).find('ul').length <= 0) {
                                if (defaults.ignoreColumn.indexOf(index) == -1) {
                                    excel += "<td>";
                                    if ($(this).children().length > 0) {
                                        $(this).filter(':visible').find('*').each(function (index, data) {
                                            if ($(this).css('display') != 'none' && $(this).children().length < 1) {
                                                excel += parseString($(this))
                                                return false;
                                            }
                                        });
                                    }
                                    else {
                                        excel += parseString($(this))
                                    }
                                    excel += "</td>";
                                }
                            }
                            colCount++
                        });
                        rowCount++;
                        excel += '</tr>'
                    }
                });

                if (defaults.footer == 'true') {

                    //two empty rows between summary and details
                    excel += "<tr>";
                    rowCount++;
                    excel += '</tr>'
                    excel += "<tr>";
                    rowCount++;
                    excel += '</tr>'
                    $(el).find('tfoot').find('tr').each(function () {
                        if ($(this).css('display') != 'none') {
                            excel += "<tr>";
                            var colCount = 0;
                            $(this).filter(':visible').find('td').each(function (index, data) {
                                if ($(this).css('display') != 'none' && $(this).find('ul').length <= 0) {
                                    if (defaults.ignoreColumn.indexOf(index) == -1) {
                                        excel += "<td>";
                                        if ($(this).children().length > 0) {
                                            $(this).filter(':visible').find('*').each(function (index, data) {
                                                if ($(this).css('display') != 'none' && $(this).children().length < 1) {
                                                    excel += parseString($(this))
                                                    return false;
                                                }
                                            });
                                        }
                                        else {
                                            excel += parseString($(this))
                                        }
                                        excel += "</td>";
                                    }
                                }
                                colCount++
                            });
                            rowCount++;
                            excel += '</tr>'
                        }
                    });

                }

                excel += '</table>'
                if (defaults.consoleLog == 'true') { console.log(excel) }
                var worksheetName = defaults.sheetName;
                worksheetName = worksheetName != null && worksheetName != "" ? worksheetName : "worksheet";
                var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:" + defaults.type + "' xmlns='http://www.w3.org/TR/REC-html40'>";
                excelFile += "<head>";
                excelFile += "<!--[if gte mso 9]>";
                excelFile += "<xml>";
                excelFile += "<x:ExcelWorkbook>";
                excelFile += "<x:ExcelWorksheets>";
                excelFile += "<x:ExcelWorksheet>";
                excelFile += "<x:Name>";
                excelFile += worksheetName;
                excelFile += "</x:Name>";
                excelFile += "<x:WorksheetOptions>";
                excelFile += "<x:DisplayGridlines/>";
                excelFile += "</x:WorksheetOptions>";
                excelFile += "</x:ExcelWorksheet>";
                excelFile += "</x:ExcelWorksheets>";
                excelFile += "</x:ExcelWorkbook>";
                excelFile += "</xml>";
                excelFile += "<![endif]-->";
                excelFile += "</head>";
                excelFile += "<body>";
                excelFile += excel;
                excelFile += "</body>";
                excelFile += "</html>";
                var base64data = "base64," + $.base64.encode(excelFile);
                var uri = 'data:application/vnd.ms-' + defaults.type + ';' + base64data;
                var docExt = 'doc';
                if (defaults.type == 'excel') { docExt = 'xls' } else if (defaults.type == 'doc') { docExt = 'doc' } else if (defaults.type == 'powerpoint') { docExt = 'ppt' }
                var downloadLink = document.createElement("a");
                downloadLink.href = uri;
                if (defaults.fileName != 'yourFileName') {
                    downloadLink.download = defaults.fileName + "." + docExt;
                } else {
                    downloadLink.download = nameOfFile + "." + docExt;
                }

                document.body.appendChild(downloadLink);
                downloadLink.click();
                document.body.removeChild(downloadLink)
            } else if (defaults.type == 'png') {
                html2canvas($(el), {
                    onrendered: function (canvas) {
                        var img = canvas.toDataURL("image/png");
                        window.open(img)
                    }
                })
            } else if (defaults.type == 'pdf') {
                var doc = new jsPDF('l', 'mm', 'a3', !0);
                doc.setFontSize(defaults.pdfFontSize);
                var startColPosition = 5;

                $(el).find('thead').find('tr').each(function () {
                    $(this).filter(':visible').find('th').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                var colPosition = startColPosition + (index * 30);
                                doc.text(colPosition, 20, parseString($(this)))
                            }
                        }
                    })
                });
                var startRowPosition = 20;
                var page = 1;
                var rowPosition = 0;
                $(el).find('tbody').find('tr').each(function (index, data) {
                    rowCalc = index + 1;
                    if (rowCalc % 26 == 0) {
                        doc.addPage();
                        page++;
                        startRowPosition = startRowPosition + 10
                    }
                    rowPosition = (startRowPosition + (rowCalc * 10)) - ((page - 1) * 280);
                    $(this).filter(':visible').find('td').each(function (index, data) {
                        if ($(this).css('display') != 'none' && $(this).find('ul').length <= 0) {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                var colPosition = startColPosition + (index * 30);
                                doc.text(colPosition, rowPosition, parseString($(this)))
                            }
                        }
                    })
                });
                doc.output('datauri');

            }
            function getColSpanForExcel(colspan, excel) {
                if (colspan > 1) {
                    for (var i = 1; i < colspan; i++)
                        excel += "<th></th>"
                }
                return excel;
            }
            function getColSpanForCSV(colspan, tdData) {
                if (colspan > 1) {
                    for (var i = 1; i < colspan; i++)
                        tdData += '""' + defaults.separator
                }
                return tdData;
            }

            function parseString(data) {
                console.log(data);
                if (defaults.htmlContent == 'true') { content_data = data.html().trim() } else { content_data = data.text().trim() }
                if (defaults.escape == 'true') { content_data = escape(content_data) }
                return content_data
            }
        }
    })
})(jQuery)
