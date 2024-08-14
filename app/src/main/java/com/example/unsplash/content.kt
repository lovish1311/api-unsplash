package com.example.unsplash

object content {
    private var dataList=ArrayList<studentDetails>()
    fun getList(map:MutableMap<String?,String?>):ArrayList<studentDetails>{
        val keyList = mapToKeyList(map)
        dataList.add(studentDetails("222","222"))
        for(i in keyList){
            dataList.add(studentDetails(map.get(i),i))
        }
        return dataList
    }
}