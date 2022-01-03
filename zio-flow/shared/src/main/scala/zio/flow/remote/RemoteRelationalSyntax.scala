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

package zio.flow.remote

import zio.flow._

class RemoteRelationalSyntax[A](self: Remote[A]) {

  final def <(that: Remote[A]): Remote[Boolean] =
    (self <= that) && (self !== that)

  final def <=(that: Remote[A]): Remote[Boolean] =
    Remote.LessThanEqual(self, that)

  final def >(that: Remote[A]): Remote[Boolean] =
    !(self <= that)

  final def >=(that: Remote[A]): Remote[Boolean] =
    (self > that) || (self === that)

  final def !==(that: Remote[A]): Remote[Boolean] =
    !(self === that)

  final def ===(that: Remote[A]): Remote[Boolean] =
    Remote.Equal(self, that)
}
