/*
 * Copyright 2021-2022 John A. De Goes and the ZIO Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zio.flow.server.flows.model

import zio.flow.runtime.ExecutorError
import zio.json.{DeriveJsonCodec, JsonCodec}
import zio.json.ast.Json
import zio.schema.Schema

import scala.annotation.nowarn

sealed trait PollResponse

object PollResponse {
  case object Running                         extends PollResponse
  final case class Died(value: ExecutorError) extends PollResponse
  final case class Failed(value: Json)        extends PollResponse
  final case class Succeeded(value: Json)     extends PollResponse

  @nowarn private implicit val executorErrorCodec: JsonCodec[ExecutorError] =
    zio.schema.codec.JsonCodec.jsonCodec(Schema[ExecutorError])
  implicit val codec: JsonCodec[PollResponse] = DeriveJsonCodec.gen
}
