package ru.skillbranch.devintensive.extensions


fun String.stripHtml(): String {

    val tagRegex = """<[^>]*>""".toRegex()
    val emptySymbolsRegex = """\s{2,}""".toRegex()

    val str =  this.replace("""&lt;""", "<")
                   .replace("""&gt;""", ">")
                   .replace("""&amp;""", "&")
                   .replace("""&#39;""", "'")
                   .replace("""&quot;""", "\"")

    val next = tagRegex.replace(str, "")
    return emptySymbolsRegex.replace(next, " ")
}



fun String.truncate(limit: Int = 16): String {

    val x = this.trim()

    return if (this.isNotBlank()) {
                if (x.length > limit) {

                    var truncated = x.substring(0, limit)

                    if ( !x[limit].isWhitespace() ) truncated = truncated.trimEnd() + "..."
                    else truncated = truncated.trimEnd()
                    return truncated

                } else {
                    x
                }
          } else {
           x
          }


}