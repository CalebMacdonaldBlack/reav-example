(ns com.calebmacdonaldblack.reav.example.pathom
  (:require [com.calebmacdonaldblack.reav.example.datascript :as example.datascript]
            [com.calebmacdonaldblack.reav.example.person :as person]
            [com.calebmacdonaldblack.reav.example.people :as people]
            [com.calebmacdonaldblack.reav.example.ui :as ui]
            [com.calebmacdonaldblack.reav.example.app :as app]
            [com.calebmacdonaldblack.reav.example.people.table :as people.table]
            [com.calebmacdonaldblack.reav.example.person.add :as person.add]))

(def index
  [;; App
   app/app-ui

   ;; UI
   ui/default-ui-id
   ui/ui-data

   ;; People
   people/people

   ;; Person
   person/person
   person/person-ui
   person/remove-person

   ;; Add Person
   person.add/add-person
   person.add/add-person-button-ui
   person.add/add-person-input-ui
   person.add/can-submit?
   person.add/change-add-person-name
   person.add/clear-add-person-name

   ;; People Table
   people.table/people-table-ui
   people.table/people-table-row-ui

   ;; Datascript
   example.datascript/dynamic-datascript-resolver])
