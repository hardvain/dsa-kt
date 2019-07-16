package dev.aravindh.src.main.kotlin.tree.bst

import dev.aravindh.src.main.kotlin.tree.*

data class BSTNode<T : Comparable<T>>(
    val value: T,
    var left: BSTNode<T>? = null,
    var right: BSTNode<T>? = null,
    override var parent: Node<T>?,
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
        this.isLeaf = this.left == null && this.right == null && this.parent != null
        this.isInternal = this.left != null && this.right != null
    }
    constructor(value: T, left: BSTNode<T>? = null, right: BSTNode<T>? = null) :
            this(value, left, right, null, emptyList(), emptyList(), 0, false, false, false, 0, 0, 0)

    fun insert(value: T) {
        if (this.value > value) {
            if (this.left != null) {
                this.left?.insert(value)
            } else {
                this.left = BSTNode(value)
            }
        } else if (this.value < value) {
            if (this.right != null) {
                this.right!!.insert(value)
            } else {
                this.right = BSTNode(value)
            }
        }
    }

    fun search(value: T): BSTNode<T>? {
        return if (this.value > value) {
            if (this.left != null) {
                this.left!!.search(value)
            } else {
                null
            }
        } else if (this.value < value) {
            if (this.right != null) {
                this.right!!.search(value)
            } else {
                null
            }
        } else {
            this
        }
    }

    override fun deleteLeftChild(){
        this.left = null
    }
    override fun deleteRightChild(){
        this.right = null
    }
}

data class BST<T : Comparable<T>>(val value: T) : Tree<T> {
    override var root: BSTNode<T>? = BSTNode(value, null, null)
    override fun search(value: T): BSTNode<T>? {
        return if (this.root != null) {
            this.root!!.search(value)
        } else {
            null
        }
    }

    override fun delete(value: T) {
        val maybeNode = this.search(value)
       if (maybeNode != null){
           maybeNode.parent!!.deleteLeftChild()
       }
    }

    override val width: Int = 0
    override val degree: Int = 0
    override val depth: Int = 0
    override val height: Int = 0

    override fun insert(value: T) {
        if (this.root != null) {
            this.root!!.insert(value)
        } else {
            this.root = BSTNode(value)
        }
    }

}