package com.gsv28rus.calendar.utils

import com.google.gson.*
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter.ISO_ZONED_DATE_TIME
import java.lang.reflect.Type

class ZoneDateTimeDeserializer : JsonDeserializer<ZonedDateTime> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ZonedDateTime {
        return ZonedDateTime.parse(json?.asJsonPrimitive?.asString, ISO_ZONED_DATE_TIME)
    }
}

class ZoneDateTimeSerializer : JsonSerializer<ZonedDateTime> {
    override fun serialize(src: ZonedDateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.format(ISO_ZONED_DATE_TIME.withZone(ZoneId.of("GMT"))))
    }
}

