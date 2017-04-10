# cjxenon

An Xenon client for modern Clojure systems, built to address the needs of
Jepsen, and probably other systems too.

Code is based on https://github.com/aphyr/verschlimmbesserung

## Installation

Via [Clojars](https://clojars.org/cjxenon), as usual.

## Usage

Require the library and define a client.

```clj
user=> (require '[cjxenon.core :as x])
nil
user=> (def c (x/connect "http://localhost:4001"))
#'user/c
```

First we'll create an node at `/atom`. All functions take a client as their
first argument.

```clj
user=> (x/reset! c :atom "hi")
```

Which will show up if we list the root:

```clj
user=> (x/get c nil)
{"atom" "hi"}
```

Let's get that key's value

```clj
user=> (x/get c :atom)
"hi"
```

A little more detail, please

```clj
user=> (x/get* c :atom)
{:action "get", :node {:key "/atom", :value "hi", :modifiedIndex 1520, :createdIndex 1520}}
user=> (meta (x/get* c :atom))
{:status 200, :leader-peer-url "http://127.0.0.1:7001", :etcd-index "1520", :raft-index "33259", :raft-term "0"}
```

Let's compare-and-set it to a new value based on the current version

```clj
user=> (x/cas! c :atom 0 "hi")
```

That same CAS will fail if we try it a second time with wrong version:

```clj
user=> (x/cas! c :atom 10 "meow")
false
```

## Credits

Kyle Kingsbury <aphyr@aphyr.com> for https://github.com/aphyr/verschlimmbesserung

## License

Distributed under the Eclipse Public License, the same as Clojure.
