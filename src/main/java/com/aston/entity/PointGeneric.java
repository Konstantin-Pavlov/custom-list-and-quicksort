package com.aston.entity;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Getter
public class PointGeneric<T extends Number> implements Comparable<PointGeneric<T>> {
    private T x;
    private T y;

    public PointGeneric(T x, T y) {
        this.x = x;
        this.y = y;
    }

    // Method to add two points
    public PointGeneric<?> add(PointGeneric<?> another) {
        return new PointGeneric<>(
                this.x.doubleValue() + another.getX().doubleValue(),
                this.y.doubleValue() + another.getY().doubleValue()
        );
    }

    // Method to subtract two points
    public PointGeneric<?> subtract(PointGeneric<?> another) {
        return new PointGeneric<>(
                this.x.doubleValue() - another.getX().doubleValue(),
                this.y.doubleValue() - another.getY().doubleValue()
        );
    }

    // Method to calculate Euclidean distance between two points
    public double distanceTo(PointGeneric<?> another) {
        double dx = this.x.doubleValue() - another.getX().doubleValue();
        double dy = this.y.doubleValue() - another.getY().doubleValue();
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Method to scale a point by a factor
    public PointGeneric<?> scale(double factor) {
        return new PointGeneric<>(this.x.doubleValue() * factor, this.y.doubleValue() * factor);
    }

    @SuppressWarnings("unchecked")
    public void translateX(T xShift) {
        this.x = (T) Double.valueOf(this.x.doubleValue() + xShift.doubleValue());
    }

    @SuppressWarnings("unchecked")
    public void translateY(T yShift) {
        this.y = (T) Double.valueOf(this.x.doubleValue() + yShift.doubleValue());
    }

    // Method to translate a point by specified amounts
    public void translateXY(T deltaX, T deltaY) {
        this.translateX(deltaX);
        this.translateY(deltaY);
    }

    // Method to clone a point
    @SuppressWarnings("unchecked")
    public PointGeneric<T> clone() {
        try {
            return (PointGeneric<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e); // This should never happen
        }
    }

    // Method to check if a point is at the origin (0, 0)
    public boolean isOrigin() {
        return this.x.doubleValue() == 0 && this.y.doubleValue() == 0;
    }

    // Method to calculate the angle between two points with respect to the positive x-axis
    public double angleTo(PointGeneric<?> another) {
        double dx = another.getX().doubleValue() - this.x.doubleValue();
        double dy = another.getY().doubleValue() - this.y.doubleValue();
        return Math.atan2(dy, dx);
    }

    // Method to determine the quadrant in which a point lies
    public int getQuadrant() {
        if (this.x.doubleValue() >= 0 && this.y.doubleValue() >= 0) return 1;
        if (this.x.doubleValue() < 0 && this.y.doubleValue() >= 0) return 2;
        if (this.x.doubleValue() < 0 && this.y.doubleValue() < 0) return 3;
        return 4;
    }

    // Method to calculate the midpoint between two points
    public PointGeneric<?> midpoint(PointGeneric<?> another) {
        double midX = (this.x.doubleValue() + another.getX().doubleValue()) / 2;
        double midY = (this.y.doubleValue() + another.getY().doubleValue()) / 2;
        return new PointGeneric<>(midX, midY);
    }

    // Method to check if two points are equal
    public boolean ixEquals(PointGeneric<? extends Number> another) {
        if (this == another) return true;
        return (this.x.doubleValue() == another.getX().doubleValue() &&
                this.y.doubleValue() == another.getY().doubleValue());
    }

    // Method to calculate the hash code for the point
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    // Method to represent the point as a string
    @Override
    public String toString() {
        return String.format("Point{x = %.3f, y = %.3f}", getX().doubleValue(), getY().doubleValue());
    }

    @Override
    public int compareTo(@NotNull PointGeneric<T> o) {
        PointGeneric<Double> origin = new PointGeneric<>(0.0, 0.0);
        double thisDistance = this.distanceTo(origin);
        double otherDistance = o.distanceTo(origin);
        return Double.compare(thisDistance, otherDistance);
    }
}
