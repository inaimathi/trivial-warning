(ns trivial-warning.core-test
  (:require [clojure.test :refer :all]
            [trivial-warning.core :refer :all]))

(deftest test-warn
  (binding [*strict?* true]
    (testing "When *strict?* is true"
      (testing "Raises an exception"
        (is (thrown? Exception (warn "Foo"))))
      (testing "The raised exception has the expected message"
        (let [msg "This is a message"]
          (is (thrown-with-msg?
               Exception (re-pattern msg)
               (warn msg)))))))
  (binding [*strict?* false]
    (testing "When *strict?* is false"
      (testing "Throws no exception"
        (is (nil? (warn "Foo"))))
      ;; (testing "Prints a message"
      ;;   (is ???))
      )))
