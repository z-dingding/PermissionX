package com.permissionx.xzt

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
Autour: zjt
Date: 2021-09-05
Description:
 **/

typealias  PermissionsCallback = (Boolean ,List<String>) -> Unit

class InvisibleFragment :Fragment() {
    //在该Fragment中没有重写onCreateView来加载某个布局，所以该Fragment是不可见的
    //函数类型的变量，作为运行时权限申请结果的回调通知方式
    //private  var callback : ((Boolean ,List<String>) -> Unit)? = null
    private  var callback : PermissionsCallback? = null


    fun requestNow(cb :((Boolean ,List<String>) -> Unit) ,vararg  permissions : String){
        callback =cb
        //进行申请运行时权限
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            //记录所有被用户拒绝的权限
            val deniedList = ArrayList<String>()
            for ((index,result) in grantResults.withIndex()) {
              if (result != PackageManager.PERMISSION_GRANTED){
                  deniedList.add(permissions[index])
              }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it( allGranted , deniedList) }
        }
    }

}