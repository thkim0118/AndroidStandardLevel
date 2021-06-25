package com.terry.common.base

import android.view.LayoutInflater
import android.view.ViewGroup

/*
 * Created by Taehyung Kim on 2021-06-26
 */
typealias ActivityInflater<B> = (LayoutInflater) -> B

typealias FragmentInflater<B> = (LayoutInflater, ViewGroup?, Boolean) -> B