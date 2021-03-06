/*
 * Copyright (C) 2016-2018 Lightbend Inc. <http://www.lightbend.com>
 */

package akka.stream.alpakka.jms.scaladsl

import javax.jms.{Connection, ConnectionFactory}

import org.apache.activemq.ActiveMQConnection

/**
 * a silly cached connection factory, not thread safe
 *
 * @param url
 */
class CachedConnectionFactory(connFactory: ConnectionFactory) extends ConnectionFactory {

  var cachedConnection: ActiveMQConnection = null

  override def createConnection(): Connection = {
    if (cachedConnection == null) {
      cachedConnection = connFactory.createConnection().asInstanceOf[ActiveMQConnection]
    }
    cachedConnection
  }

  override def createConnection(s: String, s1: String): Connection = cachedConnection
}
