(ns com.calebmacdonaldblack.reav.example.person
  (:require [com.calebmacdonaldblack.reav.example.datascript :as example.datascript]
            [com.wsscode.pathom3.connect.operation :as pco]
            [com.wsscode.pathom3.interface.eql :as p.eql]
            [datascript.core :as d]))

(def person
  (pco/resolver
    `person
    {::pco/input        [:person/id]
     ::pco/output       [:person/name]
     ::pco/dynamic-name `example.datascript/dynamic-datascript-resolver}))

(pco/defmutation remove-person
  [{:d/keys [conn]} {:person/keys [id]}]
  (d/transact! conn [[:db/retractEntity [:person/id id]]]))

(pco/defresolver person-ui
  [env {person-name :person/name :person/keys [id]}]
  {:ui/person
   ^{:key id}
   [:li id " - " person-name " - "
    [:button {:on-click #(p.eql/process-one env `(remove-person ~{:person/id id}))}
     "Remove"]]})
