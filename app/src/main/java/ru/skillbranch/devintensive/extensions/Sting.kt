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

    return if (this.isNotBlank()) {
                if (this.length > limit) {

                    var truncated = this.substring(0, limit).trimEnd()
//                    truncated = truncated.dropLast(1)
//                    return "$truncated..."


                    if ( !truncated.last().isWhitespace() ) truncated += "..."
                    else truncated = truncated.dropLast(1) + "..."

                    return truncated


                } else {
                    this
                }
          } else {
           this
          }


}