(ns com.calebmacdonaldblack.reav.example.view
  (:require [com.calebmacdonaldblack.reav.example.pathom :as example.pathom]
            [com.wsscode.pathom3.connect.built-in.plugins :as pbip]
            [com.wsscode.pathom3.connect.indexes :as pci]
            [com.wsscode.pathom3.interface.eql :as p.eql]
            [com.wsscode.pathom3.plugin :as p.plugin]))



(defn ui [{:d/keys [db conn]} attr]
  (-> (pci/register example.pathom/index)
      (assoc :d/db @db :d/conn @conn)
      (p.plugin/register pbip/mutation-resolve-params)
      (p.eql/process-one attr)))
