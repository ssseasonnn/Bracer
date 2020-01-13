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
	implementation 'com.github.ssseasonnn:Bracer:1.0.3'
}
```

## Start

### 获取参数

- 从Activity的Intent中获取参数
  
    ```kotlin
        //使用 val 和 by params
        class ParamsActivity : AppCompatActivity() {
            //默认使用该属性的名字作为Key
            //这行代码等价于：
            //val byteParams = intent.getByteExtra("byteParams", 0)
            private val byteParams by params<Byte>()

            private val intParams by params<Int>()
            private val floatParams by params<Float>()
            private val booleanParams by params<Boolean>()
            private val stringParams by params<String>()

            //使用自定义Key
            //等价于：
            //val customKeyParams = intent.getByteExtra("this is custom key", 0)
            private val customKeyParams by params<Byte>("this is custom key")
            
        }
    ```

- 从Fragment的Arguments中获取参数:
  
    ```kotlin
        //使用 val 和 by params
        class ParamsFragment : Fragment() {
            //默认使用该属性的名字作为Key
            //这行代码等价于：
            //val byteParams = arguments.getByte("byteParams", 0)
            private val byteParams by params<Byte>()
            private val intParams by params<Int>()
            private val floatParams by params<Float>()
            private val booleanParams by params<Boolean>()
            private val stringParams by params<String>()

            //使用自定义Key
            //等价于：
            //val customKeyParams = arguments.getByte("this is custom key", 0)
            private val customKeyParams by params<Byte>("this is custom key")
            
        }
    ```

### 传递参数

- 给Activity传参：

    ```kotlin
        //使用 var 和 by mutableParams
        class MutableParamsActivity : AppCompatActivity() {
            
            var byteParams by mutableParams<Byte>()
            var intParams by mutableParams<Int>()
            var floatParams by mutableParams<Float>()
            var booleanParams by mutableParams<Boolean>()
            var stringParams by mutableParams<String>()

            var customKeyParams by mutableParams<Byte>("this is custom key")
            //...
        }

        然后:

        MutableParamsActivity().apply {
            customKeyParams = 1

            byteParams = 1
            intParams = 1
            floatParams = 1f
            booleanParams = true
            stringParams = "111"
        }.start(context)
    ```

- 给Fragment传递参数:

    ```kotlin
        //使用 var 和 by mutableParams
        class MutableParamsFragment : Fragment() {
            
            var byteParams by mutableParams<Byte>()
            var intParams by mutableParams<Int>()
            var floatParams by mutableParams<Float>()
            var booleanParams by mutableParams<Boolean>()
            var stringParams by mutableParams<String>()

            var customKeyParams by mutableParams<Byte>("this is custom key")
            //...
        }

        然后:

        MutableParamsFragment().apply {
            customKeyParams = 1

            byteParams = 1
            intParams = 1
            floatParams = 1f
            booleanParams = true
            stringParams = "111"
        }
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
