package com.shiqi.oos.entity;

import java.util.ArrayList;
import java.util.List;

public class SqDiningtableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SqDiningtableExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andShopcaridIsNull() {
            addCriterion("shopCarId is null");
            return (Criteria) this;
        }

        public Criteria andShopcaridIsNotNull() {
            addCriterion("shopCarId is not null");
            return (Criteria) this;
        }

        public Criteria andShopcaridEqualTo(String value) {
            addCriterion("shopCarId =", value, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridNotEqualTo(String value) {
            addCriterion("shopCarId <>", value, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridGreaterThan(String value) {
            addCriterion("shopCarId >", value, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridGreaterThanOrEqualTo(String value) {
            addCriterion("shopCarId >=", value, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridLessThan(String value) {
            addCriterion("shopCarId <", value, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridLessThanOrEqualTo(String value) {
            addCriterion("shopCarId <=", value, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridLike(String value) {
            addCriterion("shopCarId like", value, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridNotLike(String value) {
            addCriterion("shopCarId not like", value, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridIn(List<String> values) {
            addCriterion("shopCarId in", values, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridNotIn(List<String> values) {
            addCriterion("shopCarId not in", values, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridBetween(String value1, String value2) {
            addCriterion("shopCarId between", value1, value2, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andShopcaridNotBetween(String value1, String value2) {
            addCriterion("shopCarId not between", value1, value2, "shopcarid");
            return (Criteria) this;
        }

        public Criteria andRenshuIsNull() {
            addCriterion("renshu is null");
            return (Criteria) this;
        }

        public Criteria andRenshuIsNotNull() {
            addCriterion("renshu is not null");
            return (Criteria) this;
        }

        public Criteria andRenshuEqualTo(String value) {
            addCriterion("renshu =", value, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuNotEqualTo(String value) {
            addCriterion("renshu <>", value, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuGreaterThan(String value) {
            addCriterion("renshu >", value, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuGreaterThanOrEqualTo(String value) {
            addCriterion("renshu >=", value, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuLessThan(String value) {
            addCriterion("renshu <", value, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuLessThanOrEqualTo(String value) {
            addCriterion("renshu <=", value, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuLike(String value) {
            addCriterion("renshu like", value, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuNotLike(String value) {
            addCriterion("renshu not like", value, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuIn(List<String> values) {
            addCriterion("renshu in", values, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuNotIn(List<String> values) {
            addCriterion("renshu not in", values, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuBetween(String value1, String value2) {
            addCriterion("renshu between", value1, value2, "renshu");
            return (Criteria) this;
        }

        public Criteria andRenshuNotBetween(String value1, String value2) {
            addCriterion("renshu not between", value1, value2, "renshu");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}