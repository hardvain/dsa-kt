package dev.aravindh.dsa.tree.bst

import arrow.core.Option
import dev.aravindh.dsa.tree.Node
import dev.aravindh.dsa.tree.Tree

data class BSTNode<T : Comparable<T>>(
    val value: T,
    var left: Option<BSTNode<T>>,
    var right: Option<BSTNode<T>>,
    override var parent: Option<Node<T>>,
    override var children: List<Node<T>>,
    override var siblings: List<Node<T>>,
    override var degree: Int,
    override var isRoot: Boolean,
    override var isInternal: Boolean,
    override var isLeaf: Boolean,
    override var level: Int,
    override var depth: Int,
    override var height: Int
) : Node<T> {
    init {
        this.isLeaf = this.left.isEmpty() && this.right.isEmpty() && this.parent.isDefined()
        this.isInternal = this.left.isDefined() && this.right.isDefined()
    }

    constructor(value: T, left: Option<BSTNode<T>> = Option.empty(), right: Option<BSTNode<T>> = Option.empty()) :
            this(value, left, right, Option.empty(), emptyList(), emptyList(), 0, false, false, false, 0, 0, 0)

    fun insert(value: T) {
        if (this.value > value) {
            if (this.left.isDefined()) {
                this.left.map { it.insert(value) }
            } else {
                this.left = Option.just(BSTNode(value))
            }
        } else if (this.value < value) {
            if (this.right.isDefined()) {
                this.right.map { it.insert(value) }
            } else {
                this.right = Option.just(BSTNode(value))
            }
        }
    }

    fun search(value: T): Option<BSTNode<T>> {
        return if (this.value > value) {
            if (this.left.isDefined()) {
                this.left.flatMap { it.search(value) }
            } else {
                Option.empty()
            }
        } else if (this.value < value) {
            if (this.right.isDefined()) {
                this.right.flatMap { it.search(value) }
            } else {
                Option.empty()
            }
        } else {
            Option.empty()
        }
    }

    override fun deleteLeftChild() {
        this.left = Option.empty()
    }

    override fun deleteRightChild() {
        this.right = Option.empty()
    }
}

data class BST<T : Comparable<T>>(val value: T) : Tree<T> {
    override var root: Option<BSTNode<T>> = Option.just(BSTNode(value))
    override fun search(value: T): Option<BSTNode<T>> {
        return if (this.root.isDefined()) {
            this.root.flatMap { it.search(value) }
        } else {
            Option.empty()
        }
    }

    override fun delete(value: T) {
        val maybeNode = this.search(value)
        if (maybeNode.isDefined()) {
            maybeNode.map { node -> node.parent.map { parent -> parent.deleteLeftChild() } }
        }
    }

    override val width: Int = 0
    override val degree: Int = 0
    override val depth: Int = 0
    override val height: Int = 0

    override fun insert(value: T) {
        if (this.root.isDefined()) {
            this.root.map { it.insert(value) }
        } else {
            this.root = Option.just(BSTNode(value))
        }
    }

}