
# Drone Shop

### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has: 
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task. 

The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;

> Feel free to make assumptions for the design approach. 

---

### Requirements

While implementing your solution **please take care of the following requirements**: 

#### Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

---

#### Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

---


## API Reference

#### Get all drones

```
  GET /drones
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |

#### Create a Drone

```
  POST /drones
```

| Payload  | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of drone to be created (Unique)|
| `serialNumber`      | `string` | **Required**. Serial number of drone (Unique) |
| `model`      | `string` | **Required**. Model of drone to be created. Choices given in specification|
| `weightLimit`      | `integer` | **Required**. Weight limit of the drone. Must be between 0 and 500 |
| `batteryCapacity`      | `integer` | **Required**. Battery Capacity of the drone to be created |
| `state`      | `string` | **Required**. The state of the drone. Choices given in specification |
| `medications`      | `array` | **Optional**. An array of medication IDs. An exception thrown if a medication is not found |

#### Load a drone with medications
```
  POST /drones/load
```

| Payload | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `droneId` | `integer`| **Required** ID of the drone to be loaded |
| `medications` | `array` | **Required** An array of medications to be loaded. Total load weight of medications cannot be more than drone capacity |

#### Get all medications for a drone
```
  GET /drones/{id}/medications
```

| Parameter | Type  | Description   |
| :---------| :-----| :--------------|
| `id`      | `integer`| ID of the drone to fetch medications |

#### Get drones available for loading

```
  GET /drones/available-for-loading
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |

#### Get battery percentage of a drone

```
  GET /drones/{id}/battery
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id`      | `integer`| ID of the drone to fetch  battery percentage |

#### Create Medication

```
  POST /medications
```

| Payload | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id`      | `string` | **Required**. Id of medication to be created (Unique)|
| `code`      | `string` | **Required**. Code of the medication (Regex-aware) (Unique) |
| `name`      | `string` | **Required**. Name of the medication (Regex-aware)|
| `weight`      | `integer` | **Required**. Weight of the medication.|

#### Get all medications

```
  GET /medications
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |

#### Get a single medication 

```
  GET /medications/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id`      | `integer`| ID of the medication to fetch |



#### Task

We have a scheduled task in `ScheduledTasks.java` which runs every hour. It basically performs an audit on all drones, and logs their battery levels alongside a timestamp and other relevant information

