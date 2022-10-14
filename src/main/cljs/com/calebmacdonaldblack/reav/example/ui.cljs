(ns com.calebmacdonaldblack.reav.example.ui
  (:require [com.calebmacdonaldblack.reav.example.datascript :as example.datascript]
            [com.wsscode.pathom3.connect.built-in.resolvers :as pbir]
            [com.wsscode.pathom3.connect.operation :as pco]))


(def ui-data
  (pco/resolver
    `ui-data
    {::pco/input        [:ui/id]
     ::pco/output       [:ui.person.add/name]
     ::pco/dynamic-name `example.datascript/dynamic-datascript-resolver}))

(def default-ui-id
  (pbir/constantly-resolver :ui/id :default))
