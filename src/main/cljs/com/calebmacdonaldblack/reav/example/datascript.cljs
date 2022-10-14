(ns com.calebmacdonaldblack.reav.example.datascript
  (:require [com.wsscode.pathom3.connect.operation :as pco]
            [com.wsscode.pathom3.connect.planner :as pcp]
            [com.wsscode.pathom3.format.shape-descriptor :as pfsd]
            [datascript.core :as d]))


(def dynamic-datascript-resolver
  (pco/resolver
    `dynamic-datascript-resolver
    {::pco/dynamic-resolver? true}
    (fn [{:d/keys [db] :as env} input]
      (let [query  (-> env ::pcp/node ::pcp/foreign-ast pfsd/ast->shape-descriptor pfsd/shape-descriptor->query)
            lookup (first input)]
        (d/pull db query lookup)))))
