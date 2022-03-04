![](bracer.png)

[![](https://jitpack.io/v/ssseasonnn/Bracer.svg)](https://jitpack.io/#ssseasonnn/Bracer)

# Bracer

在各个Activity或者各个Fragment之间安全快速的传递参数

*Read this in other languages: [中文](README.zh.md), [English](README.md), [Change Log](CHANGELOG.md)*

## Prepare

1. Add the JitPack repository to your build file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

```gradle
dependencies {
	implementation 'com.github.ssseasonnn:Bracer:1.0.5'
}
```

## First Blood

亲，都2020年了，还在写这样的代码吗？

```kotlin
val param1 = intent.getStringExtra("param1")
//param1 可能为空，所以我们要判空
if (param1 != null) {
    //using param1
}
```
或者

```kotlin
class ActivityB : AppCompatActivity() {
    private fun gotoActivityA() {
        val intent = Intent(this, ActivityA::class.java)
        intent.putExtra("key_1", "123")
        startActivity(intent)
    }
}

class ActivityA : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //oh shit, wtf!! 写错了key的名字，导致一直获取不到值
        val valueA = intent.getStringExtra("key_l")
        
    }
}
```

更有甚者：

```kotlin
//Oh my god! 每个Fragment都要这么写一遍吗？
class FragmentA : Fragment() {
    var a: String = ""
    var b: String = ""

    companion object {
        fun newFragment(a: String, b: String): FragmentA {
            val fragmentA = FragmentA()
            val bundle = Bundle()
            bundle.putString("key_a", a)
            bundle.putString("key_b", b)
            fragmentA.arguments = bundle
            return fragmentA
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            a = it.getString("key_a") ?: ""
            b = it.getString("key_b") ?: ""
        }
    }
}
```

我只是想简单传个参数，为什么要让我写这么多代码？？？我好累...

别怕，现在有了**Bracer!!**


## Double kill

来看看新世纪应该如何正确传递参数吧

在Fragment中获取参数：

```kotlin
class MutableParamsFragment : Fragment() {
    //基本类型
    var intParams by mutableParams<Int>()
    var booleanParams by mutableParams<Boolean>()
    var stringParams by mutableParams<String>()

    //自定义类型
    var customParams by mutableParams<CustomParams1>()

    //list
    var intListParams by mutableParams<ArrayList<Int>>()
    var stringListParams by mutableParams<ArrayList<String>>()
    
    //array
    var intArrayParams by mutableParams<IntArray>()
    var arrayCustomParams by mutableParams<Array<CustomParams1>>()

    //其他任意类型
    //...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //直接使用,不需要再手动从Arguments中读取了
        println(intParams)
        println(booleanParams)
        println(stringParams)
        println(customParams)
        println(intListParams)
        println(stringListParams)
        println(intArrayParams)

    }
}
```

如你所见，从Fragment里获取参数就是这么简单，并且非常安全！！

并且支持几乎所有的类型！

你不会再遇到空指针null，所有的参数都会有默认值；
不会遇到key写错的情况，所有的参数默认都以自身的名字作为key.

> 等同于： 
> val byteParams = arguments.getByte("byteParams", 0)
> var stringParams = arguments.getString("stringParams") ?: ""

接下来我们来见识一下如何传递参数：

```kotlin
val fragment = MutableParamsFragment().apply {
    intParams = 1  //赋值即可
    booleanParams = true
    stringParams = "123"

    customParams = CustomParams1()
    intListParams = arrayListOf(1,2,3)

    intArrayParams = IntArray(2) { it }
}

//show this Fragment
val beginTransaction = supportFragmentManager.beginTransaction()
beginTransaction.add(R.id.frameLayout, fragment, "")
beginTransaction.commit()

```

Amazing!! 是的就是这么神奇，传递参数就是这么简单！

## Double Kill

接下来看一下Activity的情况吧

```kotlin
class MutableParamsActivity : AppCompatActivity() {
    //基本类型
    var intParams by mutableParams<Int>()
    var booleanParams by mutableParams<Boolean>()
    var stringParams by mutableParams<String>()

    //自定义类型
    var customParams by mutableParams<CustomParams1>()

    //list
    var intListParams by mutableParams<ArrayList<Int>>()
    var stringListParams by mutableParams<ArrayList<String>>()
    
    //array
    var intArrayParams by mutableParams<IntArray>()
    var arrayCustomParams by mutableParams<Array<CustomParams1>>()

    //其他任意类型
    //...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //直接使用,不需要再手动从Intent中读取了
        println(intParams)
        println(booleanParams)
        println(stringParams)
        println(customParams)
        println(intListParams)
        println(stringListParams)
        println(intArrayParams)

    }
}
```

和Fragment类似，从Activity里获取参数依然这么简单，并且依旧非常安全！！
你不会再遇到空指针null，所有的参数都会有默认值；
不会遇到key写错的情况，所有的参数默认都以自身的名字作为key.

> 等同于： 
> val byteParams = intent.getByteExtra("byteParams", 0)
> var stringParams = intent.getStringExtra("stringParams") ?: ""

接下来我们来见识一下如何传递参数：

```kotlin
MutableParamsActivity().apply {
    intParams = 1  //赋值即可
    booleanParams = true
    stringParams = "123"

    customParams = CustomParams1()
    intListParams = arrayListOf(1,2,3)

    intArrayParams = IntArray(2) { it }
}.start(context)
```

？？？确认你没写错？为什么能new一个Activity？？？

是的，就是这么神奇，和Fragment的用法几乎一致，只是略微做了点骚操作！


## Triple Kill

除此之外，Bracer还支持一些其他特性.

例如自定义key：

```kotlin
var customKeyParams by mutableParams<Byte>("this is custom key")
```

或者支持自定义默认值：

```kotlin
var defaultParams by mutableParams<BigDecimal>(defaultValue = BigDecimal.ONE)
```

## 天辉获胜，GG

混淆配置

```kotlin
//只需要keep自定义的参数类型
-keep class zlc.season.bracerapp.CustomParams1 { *; }
```

### License

> ```
> Copyright 2019 Season.Zlc
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at
>
>    http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
> ```
