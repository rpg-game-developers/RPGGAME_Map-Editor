package com.rpggame.mapeditor.view.texteditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.rpggame.mapeditor.view.ImGuiView;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;
import imgui.extension.texteditor.TextEditor;
import imgui.extension.texteditor.TextEditorLanguageDefinition;
import imgui.extension.texteditor.flag.TextEditorPaletteIndex;
import imgui.flag.ImGuiFocusedFlags;
import imgui.flag.ImGuiTabItemFlags;

import java.util.HashMap;
import java.util.Map;

public class TextFileEditorView implements ImGuiView {
    private FileHandle fileHandle;
    private final TextEditor editor;
    private static final TextEditorLanguageDefinition lang;
    private int itemFlags;

    static {
        lang = new TextEditorLanguageDefinition();

        String[] keywords = {
                "await", "break", "case", "catch", "class", "const", "continue", "debugger", "default",
                "delete", "do", "else", "enum", "export", "extends", "false", "finally", "for", "function",
                "if", "implements", "import", "in", "instanceof", "interface", "let", "new", "null",
                "package", "private", "protected", "public", "return", "super", "switch", "static",
                "this", "throw", "try", "true", "typeof", "var", "void", "while", "with", "yield"
        };

        lang.setName("JavaScript");
        lang.setCommentStart("/*");
        lang.setCommentEnd("*/");
        lang.setSingleLineComment("//");
        Map<String,Integer> regex = new HashMap<>();
        regex.put("L?\\\"(\\\\.|[^\\\"])*\\\"", TextEditorPaletteIndex.String);
        regex.put("\\'[^\\']*\\'", TextEditorPaletteIndex.String);
        regex.put("0[xX][0-9a-fA-F]+[uU]?[lL]?[lL]?", TextEditorPaletteIndex.Number);
        regex.put("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)([eE][+-]?[0-9]+)?[fF]?", TextEditorPaletteIndex.Number);
        regex.put("[+-]?[0-9]+[Uu]?[lL]?[lL]?", TextEditorPaletteIndex.Number);
        regex.put("[a-zA-Z_][a-zA-Z0-9_]*", TextEditorPaletteIndex.Identifier);
        regex.put("[\\[\\]\\{\\}\\!\\%\\^\\&\\*\\(\\)\\-\\+\\=\\~\\|\\<\\>\\?\\/\\;\\,\\.]", TextEditorPaletteIndex.Punctuation);
        lang.setTokenRegexStrings(regex);
        lang.setAutoIdentation(true);
        lang.setKeywords(keywords);
    }

    public TextFileEditorView(String filePath) {
        this.fileHandle = Gdx.files.local(filePath);
        this.editor = new TextEditor();
        this.editor.setLanguageDefinition(lang);
        this.editor.setText(this.fileHandle.readString());
        this.itemFlags = -1;
    }

    @Override
    public void imGui() {
        this.editor.render(this.fileHandle.toString());

        if (ImGui.isWindowFocused(ImGuiFocusedFlags.ChildWindows)) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                save();
            }
        }

        if (this.itemFlags < 0) {
            this.editor.isTextChanged();
            this.itemFlags = 0;
        }
    }

    public int getTabItemFlags() {
        if (this.itemFlags < 0) {
            return 0;
        }
        if (this.editor.isTextChanged()) {
            this.itemFlags |= ImGuiTabItemFlags.UnsavedDocument;
        }
        return this.itemFlags;
    }

    public String getFileName() {
        return this.fileHandle.name();
    }

    public String getFilePath() {
        return this.fileHandle.toString();
    }

    public void save() {
        try {
            this.fileHandle.writeString(this.editor.getText(), false);
            this.itemFlags = 0;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void undo() {
        this.editor.undo(1);
    }

    public void redo() {
        this.editor.redo(1);
    }

    public void copy() {
        this.editor.copy();
    }

    public void cut() {
        this.editor.cut();
    }

    public void delete() {
        this.editor.delete();
    }

    public void paste() {
        this.editor.paste();
    }
}
