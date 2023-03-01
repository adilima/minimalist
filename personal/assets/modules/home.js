
function doMsg() {
    Personal.showToast("This is a message from HOME");
}

function doMarkdown() {
    let str1 = Personal.loadMarkdown("intro.md");
    let html = marked.parse(str1, {gfm: true});
    document.getElementById('preview').innerHTML = html;
}

function showBook(path) {
    let str1 = Personal.loadMarkdown(path);
    let html = marked.parse(str1, {
        gfm: true
    });
    document.getElementById('preview').innerHTML = html;
}


