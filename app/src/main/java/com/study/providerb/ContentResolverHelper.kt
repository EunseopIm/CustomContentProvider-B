package com.study.providerb

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log

class ContentResolverHelper(private var mContext: Context) {

    private var contentResolver: ContentResolver = mContext.contentResolver

    /**
     * select all items
     */
    fun getAllItems() {

        val cursor = contentResolver.query(MyContract.CONTENT_URI, null, null, null, null)

        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {

                val itemIdIndex = cursor.getColumnIndex("itemId")
                val titleIndex = cursor.getColumnIndex("title")
                val contentIndex = cursor.getColumnIndex("content")

                val id = cursor.getLong(itemIdIndex)
                val title = cursor.getString(titleIndex)
                val content = cursor.getString(contentIndex)

                Log.v(">>>", "@# id[$id] title[$title] content[$content]")
            }
        }
    }

    /**
     * select single item
     */
    fun getItem(id: Long) {

        val cursor = contentResolver.query(MyContract.CONTENT_URI, null, "id", arrayOf("$id"), null)

        if (cursor != null && cursor.count > 0) {

            while (cursor.moveToNext()) {

                val itemIdIndex = cursor.getColumnIndex("itemId")
                val titleIndex = cursor.getColumnIndex("title")
                val contentIndex = cursor.getColumnIndex("content")

                val id = cursor.getLong(itemIdIndex)
                val title = cursor.getString(titleIndex)
                val content = cursor.getString(contentIndex)

                Log.v(">>>", "@# id[$id] title[$title] content[$content]")
            }
        }
    }

    /**
     * Insert
     */
    fun insertItem(title: String, content: String) {

        val contentValues = generateItem(title, content)
        contentResolver.insert(MyContract.CONTENT_URI, contentValues)
    }

    /**
     * Remove
     */
    fun removeItem(id: Long) {

        val uriInfo = "${MyContract.URI_STRING}/$id"

        contentResolver.delete(Uri.parse(uriInfo), "id", arrayOf("$id"))
    }

    /**
     * Item 생성 (ContentValues)
     */
    private fun generateItem(title: String, content: String): ContentValues {

        val values = ContentValues()
        values.put("title", title)
        values.put("content", content)

        return values
    }

    /**
     * 커스텀 메서드 - id 가져오기
     */
    fun customMethodGetId(): String? {

        var value: String? = null

        val bundle: Bundle? = contentResolver.call(MyContract.CONTENT_URI, "getId", null, null)

        bundle?.let {

            val id = it.getString("id")
            Log.v(">>>", "customMethodGetId : $id")
            value = id
        }

        return value
    }
}