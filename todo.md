## TODO

* create simplyfied FileGroupAgg
    * CreatedEvent
    * CompletedEvent
    * CancelledEvent
    * DeliveredEvent
* SQL data for Scenrarios
    * **Sc1**: create / deleted saga
      FG1 created, completed, delivered
    * **Sc2**: incomplete FG1 outdated by FG2, expect FG1 CancelledEvent
      FG1 created
      FG2         created, completed, delivered
    * **Sc3**: completed FG1 outdated by FG2, expect FG1 CancelledEvent
      FG1 created, completed
      FG2                    created, completed, delivered