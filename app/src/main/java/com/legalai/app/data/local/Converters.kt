package com.legalai.app.data.local

import androidx.room.TypeConverter
import com.legalai.app.data.local.entity.Role
import com.legalai.app.data.local.entity.Document
import com.legalai.app.data.local.entity.MemoryType

class Converters {
    @TypeConverter
    fun fromRole(value: Role): String = value.name

    @TypeConverter
    fun toRole(value: String): Role = Role.valueOf(value)

    @TypeConverter
    fun fromDocumentType(value: Document.Type): String = value.name

    @TypeConverter
    fun toDocumentType(value: String): Document.Type = Document.Type.valueOf(value)

    @TypeConverter
    fun fromMemoryType(value: MemoryType): String = value.name

    @TypeConverter
    fun toMemoryType(value: String): MemoryType = MemoryType.valueOf(value)
}
