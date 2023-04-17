"use strict";
// Load the required modules
var http = require('http');
var url = require('url');
var fs = require('fs');
var path = require('path');
var cp = require('child_process');

//Create Service
var httpServer = http.createServer(processRequest);


var port = Math.floor(Math.random()*(7000-5000)) + 5000;

// Specify a listening interface
httpServer.listen(port, function() {
    console.log(`app is running at port:${port}`);
    console.log(`url: http://localhost:${port}`);
    cp.exec(`explorer http://localhost:${port}`, function() {});
});

// Functions that respond to requests
function processRequest(request, response) {
    //mime
    var mime = {
        "css": "text/css",
        "gif": "image/gif",
        "html": "text/html",
        "ico": "image/x-icon",
        "jpeg": "image/jpeg",
        "jpg": "image/jpeg",
        "js": "text/javascript",
        "json": "application/json",
        "pdf": "application/pdf",
        "png": "image/png",
        "svg": "image/svg+xml",
        "swf": "application/x-shockwave-flash",
        "tiff": "image/tiff",
        "txt": "text/plain",
        "wav": "audio/x-wav",
        "wma": "audio/x-ms-wma",
        "wmv": "video/x-ms-wmv",
        "xml": "text/xml"
    };

    // Cut out the identifier string inside the request
    var requestUrl = request.url;
    //url模块的parse方法 接受一个字符串，返回一个url对象,切出来路径
    var pathName = url.parse(requestUrl).pathname;

    // Decode paths to prevent Chinese garbled code
    var pathName = decodeURI(pathName);


    // Get the absolute path to the resource file
    var filePath = path.resolve(__dirname + pathName);
    console.log(filePath);
    // Get the document type of the corresponding file
    // We use path.extname to get the suffix name of the file.
    // Since the return value of extname contains "." so we use the slice method to eliminate the "." , the
    // Files without a suffix are always considered as unknown.
    var ext = path.extname(pathName);
    ext = ext ? ext.slice(1) : 'unknown';

    // Use "text/plain" for all unknown types
    var contentType = mime[ext] || "text/plain";

    fs.stat(filePath, (err, stats) => {
        if (err) {
            response.writeHead(404, { "content-type": "text/html" });
            response.end("<h1>404 Not Found</h1>");
        }
        // No error and the file exists
        if (!err && stats.isFile()) {
            readFile(filePath, contentType);
        }
        // If the path is a directory
        if (!err && stats.isDirectory()) {
            var html = "<head><meta charset = 'utf-8'/></head><body><ul>";
            // Read the file under this path
            fs.readdir(filePath, (err, files) => {
                if (err) {
                    console.log("Read File failed！");
                } else {
                    // Make a link table for easy access
                    var flag = false;
                    for (var file of files) {
                        // If it finds index.html in the directory, read this file directly
                        if (file === "index.html") {
                            readFile(filePath + (filePath[filePath.length - 1] == '/' ? '' : '/') + 'index.html', "text/html");
                            flag = true;
                            break;
                        };
                        html += `<li><a href='${file}'>${file}</a></li>`;
                    }
                    if (!flag) {
                        html += '</ul></body>';
                        response.writeHead(200, { "content-type": "text/html" });
                        response.end(html);
                    }
                }
            });
        }


        function readFile(filePath, contentType) {
            response.writeHead(200, { "content-type": contentType });
            //read file in a stream
            var stream = fs.createReadStream(filePath);
            //exception handling
            stream.on('error', function() {
                response.writeHead(500, { "content-type": contentType });
                response.end("<h1>500 Server Error</h1>");
            });
            stream.pipe(response);
        }
    });
}