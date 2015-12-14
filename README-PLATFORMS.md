![alt text](https://github.com/bitDubai/media-kit/blob/master/Readme%20Image/Fermat%20Logotype/Fermat_Logo_3D.png "Fermat
Logo")

<br><br>
## Introduction: Platforms & Super Layers

First of all we need to explain how we have separated each large structure of the framework into two concepts: _'Platforms'_ and _'Super Layers'_.
<br>
When it comes to code they are both the same, however the main difference is that the SuperLayers can be consumed by all 
Platforms (IE.: _'Communication'_ or _'Blockchain'_ Platform).

There is also another difference which is that the Platforms can consume each other in an incremental order from 
left to right, so that each Platform can consume all the previous ones. An example of this can be the following:
The _'Fermat PIP'_ Platform will only be able to consume the _'Fermat COR'_ Platform, and the _'Fermat WPD'_ Platform 
will be able to consume the two previosuly mentioned Platforms, and so on.

The Super Layers behave in a similar way, in that they can consume each other in a descending order from top to bottom,
so that each Super Layer is able to consume the previous ones. An example of this case is pretty straigtforward: 
The _'Fermat BCH'_ Super Layer is only able to consume the _'Fermat OSA'_ Super Layer, and the _'Fermat P2P'_ Super Layer
is able to consume the previously mentioned Super Layers.

As a reference, please enter the [fermat web's] (http://fermat.org/) _'Architecture'_ segment, in which you will be able to see all the previously mentioned relationships.

Before we move forward with the other topics, we think it will be usefull to point out the **Layout** of the system, in order for you to be able to put alltogether the concepts of this document. This goes as follows:

* Platforms :arrow_right: Layers :arrow_right: Plugins :arrow_right: Developers :arrow_right: Versions

-
### Layers

Now that we have covered the two main aspects of the system's architecture (Platforms & Super Layers), we will go into
detail about their components.

Within each Platform there are one or more Layers, and one of the main aspects of them is that they have the
functionality to group up several _'Plugins'_ that have the same behavior.
<br>
There is another aspect related to this, which is that the Layer, in some cases, also acts as a _'channel'_ or _'selector'_ of the necessary Plugins (and their required functionalities) given a specific request. This is best ilustrated by the following example: The _Communications Layer_ has all the required logic for recieving a request and returning a '_communication channel' prioritizing the best possible type of communication of the devices involved in the request.

-
### SubSystem

Following Layers (see reference _Layout_ in the _Introduction_) we have the Sub System Classes in which you can register all the Addons and Plugin's Developers.

-
### Developers

This last group is the one that has the logic to deliver the best Version of the Plugin, with the possibility to perform Migrations (IE.: Update the Database of a given Plugin, update files, etc.)

[//]: # (S/CER maneja los precios y cotizaciones, esta capa va a tener un index que pasa las cotizaciones)
[//]: # (IE: Blockchain platform- cryptonetwork layer, la cual posee las network de todos los XXX available)

---
##New Platform Generation

We will now continue to describe the **Platform Directory Distribution** standard for the Platforms in Fermat:

* PLATFORM_NAME/
* libraries/
  * core
  * api
* plugin
* addon
* android
* linux

As this is the standard, depending on the Platform there are many more features to include in this list.  

-
###Platform Core Classes Structure

By default, the structure we follow for the _Platform's Core Classes_ when we either need to register or create all the components is as follows:

* Platform component <code>extends AbstractPlatform</code>
  * Here the class is in charge of registering Layers.
* Layer component <code>extends AbstractLayer</code>
  * This Layer component is in charge of the register of Addons and/ or Plugins.
* AddonSubsystem component <code>extends AbstractAddonSubsystem </code>
  * The AddonSubsystem is in charge of registering of Developers.
* PluginSubsystem component <code>extends AbstractPluginSubsystem </code>
  * This PluginSubsystem component is in charge of registering Developers.

Please keep in mind that in order to obtain a readable code, we create all the components alphabetically.

-
####settings.gradle
Each platform mantains its own settings.gradle ordered by layer and component type.
<br>
Please keep the elements ordered alphabetically in order to make the code more readble.
 * Type (_addon/android/plugin_)
 * Layer name.
 * Name.

-
###Fermat Core Classes Structure
[//]: # (Insert content)

-
###FermatSystem
The _FermatSystem_ starts all the components of the platform and manages them, here we have to register all the platforms we need for the framework.
<br>
In the _build.gradle_ of the core we must also add all the pertinent references to the _Platform's Core Modules_.
When you create a new instance of the fermat system, you must provide it with an _osContext_ and an _osaPlatform_,
this is the way we found to make the framework _Multi-OS_, each operative system can provide its own _osa_ libraries and add-ons to use as needed.

* FermatSystemContext
The _FermatSystemContext_ holds all the references of the main components of Fermat, it initializes all the Platforms taking the parameters mentioned in _FermatSystem_, it also allows you to add a new _Layer_ to a given Platform.
<br>
This class also posses several methods which allows you to initialize several components such as:
 * _AddonVersion, addonDeveloper, PluginVersion, pluginDeveloper, PluginSubsystem, Layer_ or even a _Platform_ instance.

* FermatAddonManager
The _FermatAddonManager_ class centralizes all the required service actions (Start, Pause, Stop, Resume) of the _Addons_ in Fermat.

* FermatPluginManager
The _FermatPluginManager_ class centralizes all the required service actions (Start, Pause, Stop, Resume) of the _Plugins_ in Fermat.
<br>
It also allows you to get an existing or a new instance of the _FermatPluginIdsManager_.

* FermatPluginIdsManager
The _FermatPluginIdsManager_ contains all the main functionalities to manage all the Fermat _Plugins Ids_.
<br>
This class contains methods that allows you to perform a number of actions such as:
 * Charge or Create Saved Ids.
 * Obtain a _PluginsId_ or even the _PluginIdsFile_, as well as registering a new _PluginsId_ or save a _PluginIdsFile_.
