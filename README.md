# Clojure Github API App

A library containing several utility functions to utilize the github api.

Usage:

| Input                         | Output                                      |
| ----------------------------- | ------------------------------------------- |
| $> lein run "flutter/flutter" | Table of the top 10 contibutors to flutter. |

Example of output:

```
$> lein run 'facebook/react'
Getting Top 10 Contibutors for 'facebook/react' (https://github.com/facebook/react/)

| :rank |  :user-name | :num-commits |                       :user-url |
|-------+-------------+--------------+---------------------------------|
|     1 |  sophiebits |          832 |  https://github.com/sophiebits/ |
|     2 |        zpao |          820 |        https://github.com/zpao/ |
|     3 |     gaearon |          736 |     https://github.com/gaearon/ |
|     4 | sebmarkbage |          481 | https://github.com/sebmarkbage/ |
|     5 |     acdlite |          342 |     https://github.com/acdlite/ |
|     6 |    petehunt |          205 |    https://github.com/petehunt/ |
|     7 |     bvaughn |          205 |     https://github.com/bvaughn/ |
|     8 |    chenglou |          152 |    https://github.com/chenglou/ |
|     9 |       vjeux |          140 |       https://github.com/vjeux/ |
|    10 |       jimfb |          116 |       https://github.com/jimfb/ |
```

