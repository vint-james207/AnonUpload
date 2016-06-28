function getFiles(data) {
console.log(data);
    for (var i in data) {
        var elem = $("<a>");
        elem.attr("href", "files/" + data[i].realFilename);
        console.log(data[i].realFilename);
        elem.text(data[i].comment);
        $("#list").append(elem);
        $("#list").append($("<br>"));
    }
}

$.get("/files", getFiles);