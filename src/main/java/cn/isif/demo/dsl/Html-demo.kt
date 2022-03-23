package cn.isif.demo.dsl

class Body {
    var h1: String? = null

    fun h1(text: String) {
        h1 = "<h1>$text</h1>"
    }
}

class Html {
    var html: StringBuilder = StringBuilder()

    init {
        html.append("<html>")
    }

    fun body(block: Body.() -> Unit) {
        val body = Body()
        block.invoke(body)
        body.h1?.let {
            html.append("<body>$it</body>")
        }
    }

    fun title(text: String) {
        html.append("<title>$text</title>")
    }

    fun toHtml(): String {
        html.append("</html>")
        return html.toString()
    }
}

fun html(block: Html.() -> Unit) {
    val html = Html()
    block.invoke(html)
    html.toHtml().apply {
        println()
    }
}



fun main() {
    html {
        title("Title with Html")
        body {
            h1("halo halo!!!")
        }
    }
}