package com.permissionx.xzt

import androidx.fragment.app.FragmentActivity

/**
Autour: zjt
Date: 2021-09-05
Description:
 **/
/**
 * 编写对外接口部分
 */
 object  PermissionX {
 private const val TAG = "InvisibleFragment"

 fun request(activity:FragmentActivity,vararg permissions:String ,callback:PermissionsCallback){
  val fragmentManager = activity.supportFragmentManager
  //判断传入的activity是否包含了指定TAG的Fragment
  val existedFragment = fragmentManager.findFragmentByTag(TAG)
  val fragment = if (existedFragment != null){
       existedFragment as InvisibleFragment
  }else{
   val invisibleFragment = InvisibleFragment()
   //commitNow()与commit的区别是commitNow回立即执行添加操作
   fragmentManager.beginTransaction().add(invisibleFragment,TAG).commitNow()
   invisibleFragment

  }
  //permissions是数组，前面加*表示将数组转化为可变长度参数传递过去
  fragment.requestNow(callback, *permissions)
 }

}