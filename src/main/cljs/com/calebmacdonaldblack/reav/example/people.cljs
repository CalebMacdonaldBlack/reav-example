(ns com.calebmacdonaldblack.reav.example.people
  (:require [com.wsscode.pathom3.connect.operation :as pco]
            [datascript.core :as d]))

(pco/defresolver people
  [{:d/keys [db]} _input]
  {::pco/output [{:people [:person/id]}]}
  {:people
   (d/q '[:find [(pull ?e [:person/id]) ...]
          :where
          [?e :person/id]]
        db)})
