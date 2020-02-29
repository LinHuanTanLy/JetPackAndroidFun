package com.ly.wanfun.domain.bean

import androidx.databinding.BaseObservable

/**
 * 获取公众号列表
 */
data class ChaptersBean(
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: String,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)