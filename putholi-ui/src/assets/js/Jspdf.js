var jsPDF=function(){var version='20090504';var buffer='';var pdfVersion='1.3';var defaultPageFormat='a4';var pageFormats={'a3':[841.89,1190.55],'a4':[595.28,841.89],'a5':[420.94,595.28],'letter':[612,792],'legal':[612,1008]};var textColor='0 g';var page=0;var objectNumber=2;var state=0;var pages=new Array();var offsets=new Array();var lineWidth=0.200025;var pageHeight;var k;var unit='mm';var fontNumber;var documentProperties={};var fontSize=16;var pageFontSize=16;if(unit=='pt'){k=1}else if(unit=='mm'){k=72/25.4}else if(unit=='cm'){k=72/2.54}else if(unit=='in'){k=72}
var newObject=function(){objectNumber++;offsets[objectNumber]=buffer.length;out(objectNumber+' 0 obj')}
var putHeader=function(){out('%PDF-'+pdfVersion)}
var putPages=function(){var wPt=pageWidth*k;var hPt=pageHeight*k;for(n=1;n<=page;n++){newObject();out('<</Type /Page');out('/Parent 1 0 R');out('/Resources 2 0 R');out('/Contents '+(objectNumber+1)+' 0 R>>');out('endobj');p=pages[n];newObject();out('<</Length '+p.length+'>>');putStream(p);out('endobj')}
offsets[1]=buffer.length;out('1 0 obj');out('<</Type /Pages');var kids='/Kids [';for(i=0;i<page;i++){kids+=(3+2*i)+' 0 R '}
out(kids+']');out('/Count '+page);out(sprintf('/MediaBox [0 0 %.2f %.2f]',wPt,hPt));out('>>');out('endobj')}
var putStream=function(str){out('stream');out(str);out('endstream')}
var putResources=function(){putFonts();putImages();offsets[2]=buffer.length;out('2 0 obj');out('<<');putResourceDictionary();out('>>');out('endobj')}
var putFonts=function(){newObject();fontNumber=objectNumber;name='Helvetica';out('<</Type /Font');out('/BaseFont /'+name);out('/Subtype /Type1');out('/Encoding /WinAnsiEncoding');out('>>');out('endobj')}
var putImages=function(){}
var putResourceDictionary=function(){out('/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]');out('/Font <<');out('/F1 '+fontNumber+' 0 R');out('>>');out('/XObject <<');putXobjectDict();out('>>')}
var putXobjectDict=function(){}
var putInfo=function(){out('/Producer (jsPDF '+version+')');if(documentProperties.title!=undefined){out('/Title ('+pdfEscape(documentProperties.title)+')')}
if(documentProperties.subject!=undefined){out('/Subject ('+pdfEscape(documentProperties.subject)+')')}
if(documentProperties.author!=undefined){out('/Author ('+pdfEscape(documentProperties.author)+')')}
if(documentProperties.keywords!=undefined){out('/Keywords ('+pdfEscape(documentProperties.keywords)+')')}
if(documentProperties.creator!=undefined){out('/Creator ('+pdfEscape(documentProperties.creator)+')')}
var created=new Date();var year=created.getFullYear();var month=(created.getMonth()+1);var day=created.getDate();var hour=created.getHours();var minute=created.getMinutes();var second=created.getSeconds();out('/CreationDate (D:'+sprintf('%02d%02d%02d%02d%02d%02d',year,month,day,hour,minute,second)+')')}
var putCatalog=function(){out('/Type /Catalog');out('/Pages 1 0 R');out('/OpenAction [3 0 R /FitH null]');out('/PageLayout /OneColumn')}
function putTrailer(){out('/Size '+(objectNumber+1));out('/Root '+objectNumber+' 0 R');out('/Info '+(objectNumber-1)+' 0 R')}
var endDocument=function(){state=1;putHeader();putPages();putResources();newObject();out('<<');putInfo();out('>>');out('endobj');newObject();out('<<');putCatalog();out('>>');out('endobj');var o=buffer.length;out('xref');out('0 '+(objectNumber+1));out('0000000000 65535 f ');for(var i=1;i<=objectNumber;i++){out(sprintf('%010d 00000 n ',offsets[i]))}
out('trailer');out('<<');putTrailer();out('>>');out('startxref');out(o);out('%%EOF');state=3}
var beginPage=function(){page++;state=2;pages[page]='';pageHeight=pageFormats.a4[1]/k;pageWidth=pageFormats.a4[0]/k}
var out=function(string){if(state==2){pages[page]+=string+'\n'}else{buffer+=string+'\n'}}
var _addPage=function(){beginPage();out(sprintf('%.2f w',(lineWidth*k)));pageFontSize=fontSize;out('BT /F1 '+parseInt(fontSize)+'.00 Tf ET')}
_addPage();var pdfEscape=function(text){return text.replace(/\\/g,'\\\\').replace(/\(/g,'\\(').replace(/\)/g,'\\)')}
return{addPage:function(){_addPage()},text:function(x,y,text){if(pageFontSize!=fontSize){out('BT /F1 '+parseInt(fontSize)+'.00 Tf ET');pageFontSize=fontSize}
var str=sprintf('BT %.2f %.2f Td (%s) Tj ET',x*k,(pageHeight-y)*k,pdfEscape(text));out(str)},setProperties:function(properties){documentProperties=properties},addImage:function(imageData,format,x,y,w,h){},output:function(type,options){endDocument();if(type==undefined){return buffer}
if(type=='datauri'){window.open('data:application/pdf;base64,'+Base64.encode(buffer))}},setFontSize:function(size){fontSize=size}}}