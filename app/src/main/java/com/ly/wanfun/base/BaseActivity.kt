package com.ly.wanfun.base

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.ly.wanfun.R
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VM : BaseVm, DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var vm: VM

    protected var mBinding: DB? = null

    private var dialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatusBar()
        initViewDataBinding()
        lifecycle.addObserver(vm)
        //注册 UI事件
        registorDefUIChange()
        initView(savedInstanceState)
        initData()
    }

    private fun initStatusBar() {
        setMIUIStatusBarDarkIcon(this, true)
        setMeizuStatusBarDarkIcon(this, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    abstract fun layoutId(): Int
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initData()


    /**
     * DataBinding
     */
    private fun initViewDataBinding() {
        val cls =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<*>
        if (ViewDataBinding::class.java != cls && ViewDataBinding::class.java.isAssignableFrom(cls)) {
            mBinding = DataBindingUtil.setContentView(this, layoutId())
            mBinding?.lifecycleOwner = this
        } else setContentView(layoutId())
        createViewModel()
    }


    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        vm.defUI.showDialog.observe(this, Observer {
            showLoading()
        })
        vm.defUI.cancelDialog.observe(this, Observer {
            dismissLoading()
        })
        vm.defUI.toast.observe(this, Observer {
        })
        vm.defUI.lod.observe(this, Observer {
            handleEvent(it)
        })
    }

    open fun handleEvent(msg: Message) {}

    /**
     * 打开等待框
     */
    private fun showLoading() {
        if (dialog == null) {
            dialog = MaterialDialog(this)
                .cancelable(false)
                .cornerRadius(8f)
                .customView(R.layout.custom_progress_dialog_view, noVerticalPadding = true)
                .lifecycleOwner(this)
                .maxWidth(R.dimen.dialog_width)
        }
        dialog?.show()

    }

    /**
     * 关闭等待框
     */
    private fun dismissLoading() {
        dialog?.run { if (isShowing) dismiss() }
    }


    /**
     * 创建 ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val tp = type.actualTypeArguments[0]
            val tClass = tp as? Class<VM> ?: BaseVm::class.java
            vm = ViewModelProvider(this, ViewModelFactory()).get(tClass) as VM
        }
    }

    /**
     * 修改 MIUI V6  以上状态栏颜色
     */
    private fun setMIUIStatusBarDarkIcon(@NonNull activity: Activity, darkIcon: Boolean) {
        val clazz: Class<out Window?> = activity.window.javaClass
        try {
            val layoutParams =
                Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field =
                layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            val darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod(
                "setExtraFlags",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            extraFlagField.invoke(
                activity.window,
                if (darkIcon) darkModeFlag else 0,
                darkModeFlag
            )
        } catch (e: Exception) { //e.printStackTrace();
        }
    }

    /**
     * 修改魅族状态栏字体颜色 Flyme 4.0
     */
    private fun setMeizuStatusBarDarkIcon(@NonNull activity: Activity, darkIcon: Boolean) {
        try {
            val lp = activity.window.attributes
            val darkFlag =
                WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            val meizuFlags =
                WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
            darkFlag.isAccessible = true
            meizuFlags.isAccessible = true
            val bit = darkFlag.getInt(null)
            var value = meizuFlags.getInt(lp)
            value = if (darkIcon) {
                value or bit
            } else {
                value and bit.inv()
            }
            meizuFlags.setInt(lp, value)
            activity.window.attributes = lp
        } catch (e: Exception) { //e.printStackTrace();
        }
    }

}