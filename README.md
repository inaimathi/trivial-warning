# trivial-warning

A Clojure library designed to allow warnings to be used in Clojure code.

It's sometimes useful to write error `raise`ing code that should fail in testing or production environments, but succeeds with a warning message while `REPL`ing for ease of interactive development.

The `warn` function defined in `trivial-warning` gives you that functionality.

## Usage

`[trivial-warning "1.0.3-SNAPSHOT"]`


### Basics

In your code,

```
(ns ...
   (:require [trivial-warning.core :refer [warn]]))

...
(warn "This is a warning. It will throw an exception during tests and in production, but will merely print this message in REPL")
...
```

### Full Example

```
~/projects $ lein new warning-example
Generating a project called warning-example based on the 'default' template.
The default template is intended for library projects, not applications.
To see other templates (app, plugin, etc), try `lein help new`.
~/projects $ cd warning-example/
~/projects/warning-example $ lein deps
<snip...>
~/projects/warning-example $ nano project.clj
<snip...>
~/projects/warning-example $ nano src/warning_example/core.clj
<snip...>
~/projects/warning-example $ cat src/warning_example/core.clj
(ns warning-example.core
  (:require [trivial-warning.core :refer [warn]]))

(defn -main [& args]
  (warn "This is a warning. It should throw an exception in testing or production, but should only print this message in REPL."))
~/projects/warning-example $ lein run
Exception in thread "main" java.lang.Exception: This is a warning. It should throw an exception in testing or production, but should only print this message in REPL., compiling:(/tmp/form-init9053740312355901353.clj:1:73)
	at clojure.lang.Compiler.load(Compiler.java:7391)
	at clojure.lang.Compiler.loadFile(Compiler.java:7317)
	at clojure.main$load_script.invokeStatic(main.clj:275)
	at clojure.main$init_opt.invokeStatic(main.clj:277)
	at clojure.main$init_opt.invoke(main.clj:277)
	at clojure.main$initialize.invokeStatic(main.clj:308)
	at clojure.main$null_opt.invokeStatic(main.clj:342)
	at clojure.main$null_opt.invoke(main.clj:339)
	at clojure.main$main.invokeStatic(main.clj:421)
	at clojure.main$main.doInvoke(main.clj:384)
	at clojure.lang.RestFn.invoke(RestFn.java:421)
	at clojure.lang.Var.invoke(Var.java:383)
	at clojure.lang.AFn.applyToHelper(AFn.java:156)
	at clojure.lang.Var.applyTo(Var.java:700)
	at clojure.main.main(main.java:37)
Caused by: java.lang.Exception: This is a warning. It should throw an exception in testing or production, but should only print this message in REPL.
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at clojure.lang.Reflector.invokeConstructor(Reflector.java:180)
	at trivial_warning.core$warn.invokeStatic(core.clj:16)
	at trivial_warning.core$warn.invoke(core.clj:15)
	at warning_example.core$_main.invokeStatic(core.clj:5)
	at warning_example.core$_main.doInvoke(core.clj:4)
	at clojure.lang.RestFn.invoke(RestFn.java:397)
	at clojure.lang.Var.invoke(Var.java:375)
	at user$eval5.invokeStatic(form-init9053740312355901353.clj:1)
	at user$eval5.invoke(form-init9053740312355901353.clj:1)
	at clojure.lang.Compiler.eval(Compiler.java:6927)
	at clojure.lang.Compiler.eval(Compiler.java:6917)
	at clojure.lang.Compiler.load(Compiler.java:7379)
	... 14 more
~/projects/warning-example $ lein repl
nREPL server started on port 40877 on host 127.0.0.1 - nrepl://127.0.0.1:40877
REPL-y 0.3.7, nREPL 0.2.12
Clojure 1.8.0
OpenJDK 64-Bit Server VM 1.8.0_131-11
    Docs: (doc function-name-here)
          (find-doc "part-of-name-here")
  Source: (source function-name-here)
 Javadoc: (javadoc java-object-or-class-here)
    Exit: Control+D or (exit) or (quit)
 Results: Stored in vars *1, *2, *3, an exception in *e

warning-example.core=> (-main)
WARNING: This is a warning. It should throw an exception in testing or production, but should only print this message in REPL.
nil
warning-example.core=>
```

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
