
function doMsg() {
    AndroidObject.showToast("main.js started");
}

ace.config.set("basePath", "https://cdn.jsdelivr.net/npm/ace-builds@1.12.3/src-noconflict");
ace.config.setModuleUrl("ace/mode/markdown", "https://cdn.jsdelivr.net/npm/ace-builds@1.12.3/src-noconflict/mode-markdown.js");
ace.config.setModuleUrl("ace/theme/github", "https://cdn.jsdelivr.net/npm/ace-builds@1.12.3/src-noconflict/theme-github.js");

var editor = ace.edit("editor", {
    mode: 'ace/mode/markdown',
    theme: 'ace/theme/github'
});
editor.setValue("# My Document\n\nTulis artikelnya di sini...");

previewData = () => {
    let html = marked.parse(editor.getValue(), {
        gfm:true,
        highlight: function(code, lang, cb) {
            let res = hljs.highlight(code, {language: lang}).value;
            if (cb) {
                cb(null, res);
            }
            return res;
        }
    });
    document.getElementById("preview").innerHTML = html;
}

openLocal = async () => {
    const options = {
        types: [
            {
                description: "All Markdown files",
                accept: {
                    'text/markdown': [ '.md' ]
                }
            },
            {
                description: "Plain text files",
                accept: {
                    'text/plain': [ '.txt' ]
                }
            }
        ],
        excludeAcceptAllOptions: true,
        multiple: false
    };

    let filehandle;
    [filehandle] = await window.showOpenFilePicker(options);
    await filehandle.getFile()
        .then((response) => response.text())
        .then((txt) => editor.setValue(txt));
}

saveLocal = async () => {
    const options = {
        types: [
            {
                description: 'All Markdown Files',
                accept: {
                    'text/markdown': ['.md'],
                },
            },
        ],
    };
    
    const handle = await window.showSaveFilePicker(options);
    const writable = await handle.createWritable();
    // Write the contents of the file to the stream.
    await writable.write(editor.getValue());
    // Close the file and write the contents to disk.
    await writable.close();
}

