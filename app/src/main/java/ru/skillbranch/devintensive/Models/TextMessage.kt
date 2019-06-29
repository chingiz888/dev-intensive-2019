package ru.skillbranch.devintensive.Models

import ru.skillbranch.devintensive.Extensions.humanizeDiff
import java.util.*


class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String?
) : BaseMessage(id, from, chat, isIncoming, date) {

    override fun formatMessage(): String = "${from?.firstName} " +
            "${if(isIncoming) "получил" else "отправил"} соообщение " +
            "$text " +
            "${date.humanizeDiff()}"
}