package com.ly.wanfun.base

import android.graphics.CornerPathEffect
import android.text.method.SingleLineTransformationMethod
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.ly.wanfun.event.SingleLiveEvent
import com.ly.wanfun.net.ExceptionHandle
import com.ly.wanfun.net.IBaseResponse
import com.ly.wanfun.net.ResponseThrowable
import com.ly.wanfun.utils.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.ResponseCache

open class BaseVm : AndroidViewModel(Utils.getApp()), LifecycleObserver {


    inner class UIChange {
        val showDialog by lazy { SingleLiveEvent<String>() }
        val cancelDialog by lazy { SingleLiveEvent<Void>() }
        val toast by lazy { SingleLiveEvent<String>() }
        val lod by lazy { SingleLiveEvent<Message>()}

    }

    val defUI: UIChange by lazy {
        UIChange()
    }

    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        block()
    }

    fun <T> launchFlow(block: suspend () -> T): Flow<T> {
        return flow {
            emit(block())
        }
    }

    fun launchGo(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit = {
            defUI.toast.postValue("${it.code}:${it.errMsg}")
        },
        complete: () -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        if (isShowDialog) defUI.showDialog.call()
        launchUI {
            handleException(
                withContext(Dispatchers.IO) { block },
                { error(it) },
                {
                    defUI.cancelDialog.call()
                    complete()
                }
            )
        }
    }




    /**
     * 请求结果过滤
     */
    private suspend fun <T> executeResponse(
        response: IBaseResponse<T>,
        success: suspend CoroutineScope.(T) -> Unit
    ) {
        coroutineScope {
            if (response.isSuccess()) success(response.data())
            else throw ResponseThrowable(response.code(), response.msg())
        }
    }

    /**
     * 异常统一处理
     */
    private suspend fun handleException(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                block()
            } catch (e: Throwable) {
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }

}