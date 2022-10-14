(ns com.calebmacdonaldblack.reav.example.app
  (:require [com.wsscode.pathom3.connect.operation :as pco]))


(pco/defresolver app-ui
  [{:ui/keys [people-table]}]
  {:ui/app [:div
            [:h1 "People Database"]
            people-table]})
