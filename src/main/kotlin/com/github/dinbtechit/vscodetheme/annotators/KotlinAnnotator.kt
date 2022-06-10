package com.github.dinbtechit.vscodetheme.annotators

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiTypeElement
import com.intellij.psi.util.elementType
import com.intellij.util.ObjectUtils
import org.jetbrains.kotlin.lexer.KtKeywordToken

class KotlinAnnotator : BaseAnnotator() {
    companion object {
        private val DEFAULT_KEYWORD: TextAttributesKey = ObjectUtils.notNull(
            TextAttributesKey.find("DEFAULT_KEYWORD"), DefaultLanguageHighlighterColors.KEYWORD
        )
        val SECONDARY_KEYWORD: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
            "JAVA.SECONDARY_KEYWORD",
            DEFAULT_KEYWORD
        )

        val TYPE_KEYWORD: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
            "JAVA.TYPE_KEYWORD",
            DEFAULT_KEYWORD
        )
    }

    override fun getKeywordType(element: PsiElement): TextAttributesKey? {
        var type: TextAttributesKey? = null
        if (element.elementType is KtKeywordToken) {
            when (element.text) {
                "return", "as" -> type = SECONDARY_KEYWORD
                "if", "else", "when", "default", "break", "continue" -> type = SECONDARY_KEYWORD
                "try", "catch", "finally", "throw" -> type = SECONDARY_KEYWORD
                "for", "while", "do" -> type = SECONDARY_KEYWORD
                else -> {}
            }
        }

        when (element.parent) {
            is PsiTypeElement -> type = TYPE_KEYWORD
            else -> {}
        }


        return type
    }

}