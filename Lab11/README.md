# Lab 11
## Browsing GemFire MBeans through JConsole
You can browse all the GemFire MBeans in your distributed system by using JConsole.

To view GemFire MBeans through JConsole, perform the following steps:
1. Start a gfsh prompt.
2. Connect to a running distributed system by either connecting to a locator with an embedded JMX Manager or connect directly to a JMX Manager. For example:
`
gfsh>connect --locator=GemFireStymon[10334]
`
or
`
gfsh>connect --jmx-manager=GemFireStymon[1099]
`
3. Start JConsole:
`
gfsh>start jconsole
`
If successful, the message Running JDK JConsole appears. The JConsole application launches and connects directly to the JMX Manager using RMI.
4. On the JConsole screen, click on the MBeans tab. Expand GemFire. Then expand each MBean to browse individual MBean attributes, operations and notifications.

