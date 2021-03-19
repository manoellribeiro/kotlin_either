sealed class Either<out L, out R> {

    class Left<out L>(val value: L) : Either<L, Nothing>()
    class Right<out R>(val value: R) : Either<Nothing, R>()

    fun <T> fold(left: (L) -> T, right: (R) -> T): T = when (this) {
        is Left -> left(this.value)
        is Right -> right(this.value)
    }

    fun leftProjection(): Projection<L> = when (this) {
        is Left -> Projection.ValueProjection(this.value)
        is Right -> Projection.EmptyProjection<L>()
    }

    fun isLeft(): Boolean = when (this) {
        is Left -> true
        is Right -> false
    }

    fun rightProjection(): Projection<R> = when (this) {
        is Left -> Projection.EmptyProjection<R>()
        is Right -> Projection.ValueProjection(this.value)
    }

    fun isRight() = when (this) {
        is Left -> false
        is Right -> true
    }
}




