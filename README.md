![](bracer.png)

[![](https://jitpack.io/v/ssseasonnn/Bracer.svg)](https://jitpack.io/#ssseasonnn/Bracer)

# Bracer

Pass parameters safely and quickly between activities or fragments.

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
	implementation 'com.github.ssseasonnn:Bracer:1.0.2
}
```

## Start

### Get Parameters

- Get parameters from Activity's Intent:
  
    ```kotlin
        //Use val and by params
        class ParamsActivity : AppCompatActivity() {
            //Get the parameter using the name of the property as the key
            //This code is equivalent to：
            //val byteParams = intent.getByteExtra("byteParams", 0)
            private val byteParams by params<Byte>()

            private val intParams by params<Int>()
            private val floatParams by params<Float>()
            private val booleanParams by params<Boolean>()
            private val stringParams by params<String>()

            //Get basic type params with custom key
            //This code is equivalent to：
            //val customKeyParams = intent.getByteExtra("this is custom key", 0)
            private val customKeyParams by params<Byte>("this is custom key")
            
        }
    ```

- Get parameters from Fragment's Arguments:
  
    ```kotlin
        //Use val and by params
        class ParamsFragment : Fragment() {
            //Get the parameter using the name of the property as the key
            //This code is equivalent to：
            //val byteParams = arguments.getByte("byteParams", 0)
            private val byteParams by params<Byte>()

            private val intParams by params<Int>()
            private val floatParams by params<Float>()
            private val booleanParams by params<Boolean>()
            private val stringParams by params<String>()

            //Get basic type params with custom key
            //This code is equivalent to：
            //val customKeyParams = arguments.getByte("this is custom key", 0)
            private val customKeyParams by params<Byte>("this is custom key")
            
        }
    ```

### Pass Parameters

- Pass parameters to Activity:

    ```kotlin
        //Use var and by mutableParams
        class MutableParamsActivity : AppCompatActivity() {
            var byteParams by mutableParams<Byte>()

            var intParams by mutableParams<Int>()
            var floatParams by mutableParams<Float>()
            var booleanParams by mutableParams<Boolean>()
            var stringParams by mutableParams<String>()

            var customKeyParams by mutableParams<Byte>("this is custom key")
            //...
        }

        Then:

        MutableParamsActivity().apply {
            customKeyParams = 1

            byteParams = 1
            intParams = 1
            floatParams = 1f
            booleanParams = true
            stringParams = "111"
        }.start(context)
    ```

- Pass parameters to Fragment:

    ```kotlin
        //Use var and by mutableParams
        class MutableParamsFragment : Fragment() {
            //Should use var and mutableParams
            var byteParams by mutableParams<Byte>()
            var intParams by mutableParams<Int>()
            var floatParams by mutableParams<Float>()
            var booleanParams by mutableParams<Boolean>()
            var stringParams by mutableParams<String>()

            var customKeyParams by mutableParams<Byte>("this is custom key")
            //...
        }

        Then:
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
