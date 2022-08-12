package com.study.providerb

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log

class ContentResolverHelper(private var mContext: Context) {

    companion object {

        private const val TABLE_NAME = "item"
        private const val AUTHORITY = "com.study.providera.MyContentProvider"
        private const val URL = "content://$AUTHORITY/$TABLE_NAME"
        val CONTENT_URI: Uri = Uri.parse(URL)
    }

    private var contentResolver: ContentResolver = mContext.contentResolver

    /**
     * select all items
     */
    fun getAllItems() {

        val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)

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

        val cursor = contentResolver.query(CONTENT_URI, null, "id", arrayOf("$id"), null)

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
        contentResolver.insert(CONTENT_URI, contentValues)
    }

    /**
     * Remove
     */
    fun removeItem(id: Long) {

        val url = "$URL/$id"

        contentResolver.delete(Uri.parse(url), "id", arrayOf("$id"))
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

    fun customMethodGetId() {

        val bundle: Bundle? = contentResolver.call(CONTENT_URI, "getId", null, null)

        bundle?.let {

            Log.v(">>>", "customMethodGetId : ${it.getString("id")}")

        }
    }
}